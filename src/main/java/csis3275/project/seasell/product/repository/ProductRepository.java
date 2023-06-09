package csis3275.project.seasell.product.repository;

import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.user.model.AppUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByStatus(ProductStatus status);

    List<Product> findAllByStatusAndSellerNot(ProductStatus status, AppUser seller);

    List<Product> findTop4ByStatusAndSellerNotOrderByCreatedAtDesc(ProductStatus status, AppUser seller);

    Optional<Product> findById(int id);

    List<Product> findAllBySeller(AppUser seller);

    List<Product> findAllBySellerAndStatus(AppUser seller, ProductStatus status);
}
