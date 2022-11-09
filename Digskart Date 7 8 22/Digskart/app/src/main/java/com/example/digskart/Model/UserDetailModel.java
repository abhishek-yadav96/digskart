package com.example.digskart.Model;

public class UserDetailModel {
    String UserId;
    String Name;
    String ReferralCode;
    String Email;
    String Mobile;
    String Password;
    String FriendsCode;
    String FcmId;
    String ProfileImg;
    String CountryCode;
    String Balance;
    String Message;

    public UserDetailModel(String userId, String name, String referralCode, String email, String mobile, String password, String friendsCode, String fcmId, String profileImg, String countryCode, String balance, String message) {
        UserId = userId;
        Name = name;
        ReferralCode = referralCode;
        Email = email;
        Mobile = mobile;
        Password = password;
        FriendsCode = friendsCode;
        FcmId = fcmId;
        ProfileImg = profileImg;
        CountryCode = countryCode;
        Balance = balance;
        Message = message;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getReferralCode() {
        return ReferralCode;
    }

    public void setReferralCode(String referralCode) {
        ReferralCode = referralCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFriendsCode() {
        return FriendsCode;
    }

    public void setFriendsCode(String friendsCode) {
        FriendsCode = friendsCode;
    }

    public String getFcmId() {
        return FcmId;
    }

    public void setFcmId(String fcmId) {
        FcmId = fcmId;
    }

    public String getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String profileImg) {
        ProfileImg = profileImg;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
