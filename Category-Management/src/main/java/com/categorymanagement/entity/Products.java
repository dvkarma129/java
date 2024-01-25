package com.categorymanagement.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull(message = "title cannot be null")
    @NotBlank(message = "title required")
    @Size(max = 255, message = "Title must be less than or equal to 255 characters")
    private String title;

	@NotNull(message = "description cannot be null")
    @NotBlank(message = "description required")
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

	@NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be a positive and it cannot be negative")
    private int quantity;

    @NotNull(message = "price cannot be null")
    @Positive(message = "pricemust be a positive and it cannot be negative")
    private Long price;

	@NotNull(message = "brand cannot be null")
    @NotBlank(message = "brand required")
    @Size(max = 255, message = "Brand must be less than or equal to 255 characters")
    private String brand;

	@NotNull(message = "sku cannot be null")
    @Column(unique = true)
    @NotBlank(message = "sku crequired")
    @Size(max = 10, message = "SKU must be less than or equal to 50 characters")
    @Pattern(regexp = "^[A-Z0-9]{10}$", message = "Product SKU must be alphanumeric")
    private String sku;

    @Lob
    @Column(length = 10485760)
    @JsonIgnore
    private byte[] image;

    //unidirectional mapping for avaoiding collectionSerializerException
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<Category> categories;
}
