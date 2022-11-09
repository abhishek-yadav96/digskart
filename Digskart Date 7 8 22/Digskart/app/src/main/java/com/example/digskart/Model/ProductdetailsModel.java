package com.example.digskart.Model;

public class ProductdetailsModel {
    String ImageUrl;
    String PackageText1;
    String ReviewTexts;
    String ProductId;
    String ProductParentId;
    String CategoryId;
    String ProductName;
    String OldPrice;
    String NewPrice;
    String LongDesc;
    String Measurement;
    String Unit;
    String CategoryName;
    String Message;
    String ProductType;
    String OfferDetails;
    String DeliveryDays;
    String RevisionTimes;
    String PackageText;
    String UnitId;
    String SampleVideoUrl;
    Boolean IsActive;
    Boolean IsDeleted;

    public ProductdetailsModel(String imageUrl, String packageText1, String reviewTexts, String productId, String productParentId, String categoryId, String productName, String oldPrice, String newPrice, String longDesc, String measurement, String unit, String categoryName, String message, String productType, String offerDetails, String deliveryDays, String revisionTimes, String packageText, String unitId, String sampleVideoUrl, Boolean isActive, Boolean isDeleted) {
        ImageUrl = imageUrl;
        PackageText1 = packageText1;
        ReviewTexts = reviewTexts;
        ProductId = productId;
        ProductParentId = productParentId;
        CategoryId = categoryId;
        ProductName = productName;
        OldPrice = oldPrice;
        NewPrice = newPrice;
        LongDesc = longDesc;
        Measurement = measurement;
        Unit = unit;
        CategoryName = categoryName;
        Message = message;
        ProductType = productType;
        OfferDetails = offerDetails;
        DeliveryDays = deliveryDays;
        RevisionTimes = revisionTimes;
        PackageText = packageText;
        UnitId = unitId;
        SampleVideoUrl = sampleVideoUrl;
        IsActive = isActive;
        IsDeleted = isDeleted;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPackageText1() {
        return PackageText1;
    }

    public void setPackageText1(String packageText1) {
        PackageText1 = packageText1;
    }

    public String getReviewTexts() {
        return ReviewTexts;
    }

    public void setReviewTexts(String reviewTexts) {
        ReviewTexts = reviewTexts;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductParentId() {
        return ProductParentId;
    }

    public void setProductParentId(String productParentId) {
        ProductParentId = productParentId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
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

    public String getLongDesc() {
        return LongDesc;
    }

    public void setLongDesc(String longDesc) {
        LongDesc = longDesc;
    }

    public String getMeasurement() {
        return Measurement;
    }

    public void setMeasurement(String measurement) {
        Measurement = measurement;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getOfferDetails() {
        return OfferDetails;
    }

    public void setOfferDetails(String offerDetails) {
        OfferDetails = offerDetails;
    }

    public String getDeliveryDays() {
        return DeliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        DeliveryDays = deliveryDays;
    }

    public String getRevisionTimes() {
        return RevisionTimes;
    }

    public void setRevisionTimes(String revisionTimes) {
        RevisionTimes = revisionTimes;
    }

    public String getPackageText() {
        return PackageText;
    }

    public void setPackageText(String packageText) {
        PackageText = packageText;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getSampleVideoUrl() {
        return SampleVideoUrl;
    }

    public void setSampleVideoUrl(String sampleVideoUrl) {
        SampleVideoUrl = sampleVideoUrl;
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
}
