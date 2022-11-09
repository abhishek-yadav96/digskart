package com.example.digskart.Model;

public class CategoryModel {
    String CategoryId;
    String CategoryName;
    String CategorySubtitle;
    String CategoryImage;
    String MainImage;
    String TotalProduct;
    Boolean IsActive;
    Boolean IsDeleted;
    String CreatedById;
    String CreatedOn;
    String ModifiedById;
    String ModifiedOn;

    public CategoryModel(String categoryId, String categoryName, String categorySubtitle, String categoryImage, String mainImage, String totalProduct, Boolean isActive, Boolean isDeleted, String createdById, String createdOn, String modifiedById, String modifiedOn) {
        CategoryId = categoryId;
        CategoryName = categoryName;
        CategorySubtitle = categorySubtitle;
        CategoryImage = categoryImage;
        MainImage = mainImage;
        TotalProduct = totalProduct;
        IsActive = isActive;
        IsDeleted = isDeleted;
        CreatedById = createdById;
        CreatedOn = createdOn;
        ModifiedById = modifiedById;
        ModifiedOn = modifiedOn;
    }


    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategorySubtitle() {
        return CategorySubtitle;
    }

    public void setCategorySubtitle(String categorySubtitle) {
        CategorySubtitle = categorySubtitle;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    public String getMainImage() {
        return MainImage;
    }

    public void setMainImage(String mainImage) {
        MainImage = mainImage;
    }

    public String getTotalProduct() {
        return TotalProduct;
    }

    public void setTotalProduct(String totalProduct) {
        TotalProduct = totalProduct;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    public String getCreatedById() {
        return CreatedById;
    }

    public void setCreatedById(String createdById) {
        CreatedById = createdById;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getModifiedById() {
        return ModifiedById;
    }

    public void setModifiedById(String modifiedById) {
        ModifiedById = modifiedById;
    }

    public String getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        ModifiedOn = modifiedOn;
    }
}
