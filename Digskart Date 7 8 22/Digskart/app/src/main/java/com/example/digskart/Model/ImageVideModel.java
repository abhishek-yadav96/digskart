package com.example.digskart.Model;

public class ImageVideModel {
    String Image;
    String Video;

    public ImageVideModel(String image, String video) {
        Image = image;
        Video = video;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }
}
