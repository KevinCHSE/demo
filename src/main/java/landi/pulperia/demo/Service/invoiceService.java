package landi.pulperia.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import landi.pulperia.demo.DTOs.InvoiceDTO;
import landi.pulperia.demo.DTOs.InvoiceDetailsDTO;
import landi.pulperia.demo.DTOs.PurchaseReport.PurchaseDTO;
import landi.pulperia.demo.DTOs.PurchaseReport.PurchasesItem;
import landi.pulperia.demo.DTOs.PurchaseReport.PurchasesQuery;
import landi.pulperia.demo.DTOs.PurchaseReport.PurchasesReportDTO;
import landi.pulperia.demo.Entities.Client;
import landi.pulperia.demo.Entities.Invoice;
import landi.pulperia.demo.Entities.Products;
import landi.pulperia.demo.Entities.invoiceDetails;
import landi.pulperia.demo.Interfaces.invoiceServiceInterface;
import landi.pulperia.demo.Repositories.InvoiceRepository;
import landi.pulperia.demo.Repositories.clientRepository;
import landi.pulperia.demo.Repositories.invoiceDetailsRepository;
import landi.pulperia.demo.Repositories.productRepository;

@Service 
public class invoiceService implements invoiceServiceInterface{

    private final InvoiceRepository invoiceRepository;
    private final clientRepository clientRepository;
    private final productRepository productRepository;
    private final invoiceDetailsRepository detailsRepository;

    public invoiceService(clientRepository clientRepository, invoiceDetailsRepository detailsRepository, InvoiceRepository invoiceRepository, productRepository productRepository) {
        this.clientRepository = clientRepository;
        this.detailsRepository = detailsRepository;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
    }

    



    @Override
    @Transactional(readOnly=true)
    public List<Invoice> invoiceList() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Invoice> getInvoiceDetails(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public Invoice save(InvoiceDTO invoiceDTO) {
        Client client= clientRepository.findById(invoiceDTO.getClientId()).orElseThrow();
        int invoiceTotal=0;

        Invoice invoice=new Invoice();
        invoice.setClient(client);
        invoice.setDate(LocalDate.now());
        invoice.setPayment(invoiceDTO.getPayment());
        

        invoiceRepository.save(invoice);
        for (InvoiceDetailsDTO item : invoiceDTO.getItems()) {
            Products product=productRepository.findById(item.getProductId()).orElseThrow();
            
            invoiceDetails invoiceDetails=new invoiceDetails();
            invoiceDetails.setInvoice(invoice);
            invoiceDetails.setProduct(product);
            invoiceDetails.setPrice( (product.getPrice()*item.getAmount()) );
            invoiceDetails.setUnitPrice(product.getPrice());
            invoiceDetails.setAmount(item.getAmount());
            
            //invoice's total
            invoiceTotal+=invoiceDetails.getPrice();

            if (product.getStock() < item.getAmount()) {
                throw new IllegalArgumentException("Stock insuficiente para " + product.getName());
            }
            //amount of products
            product.setStock(product.getStock()-item.getAmount());

            detailsRepository.save(invoiceDetails);


        }

        if (invoice.getPayment().equals("Contado")) {
            invoice.setPaid(true);
            client.setTotalSpent(client.getTotalSpent()+invoiceTotal);
        }else{
            invoice.setPaid(false);
            client.setUsedCredit(client.getUsedCredit()+invoiceTotal);
            client.setTotalSpent(client.getTotalSpent()+invoiceTotal);
        }

        return  invoice;
    }

    @Transactional 
    public PurchasesReportDTO getReport(PurchasesQuery query){
        List<Invoice> invoiceList = invoiceRepository.getReport(query.getIdClient(), query.getStartDate(), query.getEndDate());

        List<PurchaseDTO> purchasesList = new ArrayList<>();

        int total = 0;
        int cash = 0;
        int onCredit = 0;

        for (Invoice invoice : invoiceList) {
            List<invoiceDetails> details = detailsRepository.getDetailsByInvoice(invoice.getIdInvoice());
            List<PurchasesItem> purchasesItemList = new ArrayList<>();
            int totalFactura = 0;

            for (invoiceDetails item : details) {
                PurchasesItem newItem = new PurchasesItem();
                newItem.setItem(item.getProduct().getName());
                newItem.setAmount(item.getAmount());
                purchasesItemList.add(newItem);

                totalFactura += item.getPrice();
            }

            PurchaseDTO newPurchase = new PurchaseDTO();
            newPurchase.setDate(invoice.getDate());
            newPurchase.setItems(purchasesItemList);
            newPurchase.setPayment(invoice.getPayment());
            newPurchase.setTotal(totalFactura);
            purchasesList.add(newPurchase);

            total += totalFactura;
            if (invoice.getPayment().equals("Credito")) {
                onCredit += totalFactura;
            } else {
                cash += totalFactura;
            }
        }

        return new PurchasesReportDTO(purchasesList, total, cash, onCredit);
        }



}
