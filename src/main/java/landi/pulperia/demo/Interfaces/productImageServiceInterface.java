package landi.pulperia.demo.Interfaces;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import landi.pulperia.demo.Entities.productImage;

public interface productImageServiceInterface {
    productImage saveImage(Integer id, MultipartFile imagFile);
    Optional<productImage>findImageById(Integer id);
}
