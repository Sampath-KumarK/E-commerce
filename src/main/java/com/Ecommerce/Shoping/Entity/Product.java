
package com.Ecommerce.Shoping.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Product name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Product price is required")
    @PositiveOrZero(message = "Product price must be positive")
    private Double price;

    @Column(nullable = false)
    @NotBlank(message = "Product description is required")
    private String description;

    @Column(nullable = false)
    private Double rating = 0.0;

    private String seller;

    private Integer stock;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductReview> reviews = new ArrayList<>();

    public Product() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    // Automatically calculate number of reviews

    public Integer getNumOfReviews() {
        return reviews == null ? 0 : reviews.size();
    }

    // Automatically calculate average rating

    public Double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        return reviews.stream()
                .filter(review -> review.getRating() != null)
                .mapToInt(ProductReview::getRating)
                .average()
                .orElse(0.0);
    }

    // Helper methods to maintain relationships

    public void addImage(ProductImage image) {
        images.add(image);
        image.setProduct(this);
    }

    public void removeImage(ProductImage image) {
        images.remove(image);
        image.setProduct(null);
    }

    public void addReview(ProductReview review) {
        reviews.add(review);
        review.setProduct(this);
    }

    public void removeReview(ProductReview review) {
        reviews.remove(review);
        review.setProduct(null);
    }
}
