package com.example.digskart.Model;

public class PopularProductModel {
    String ProductId;
    String ProductName;
    String ProductType;
    String OldPrice;
    String NewPrice;
    String CategoryName;
    String ImageUrl;
    String Message;

    public PopularProductModel(String productId, String productName, String productType, String oldPrice, String newPrice, String categoryName, String imageUrl, String message) {
        ProductId = productId;
        ProductName = productName;
        ProductType = productType;
        OldPrice = oldPrice;
        NewPrice = newPrice;
        CategoryName = categoryName;
        ImageUrl = imageUrl;
        Message = message;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(String oldPrice) {
        OldPrice = oldPrice;
    }

    public String getNewPrice() {
        return NewPrice;
    }

    public void setNewPrice(String newPrice) {
        NewPrice = newPrice;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
