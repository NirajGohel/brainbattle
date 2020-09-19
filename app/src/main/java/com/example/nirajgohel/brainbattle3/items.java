package com.example.nirajgohel.brainbattle3;

public class items {
    private String sr_no;
    private String plyr_name;
    private int photo_profile;
    private int photo_trophy;
    private String plyr_trophy;

    public String getSr_no() {
        return sr_no;
    }

    public void setSr_no(String sr_no) {
        this.sr_no = sr_no;
    }

    public String getPlyr_name() {
        return plyr_name;
    }

    public void setPlyr_name(String plyr_name) {
        this.plyr_name = plyr_name;
    }

    public int getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(int photo_profile) {
        this.photo_profile = photo_profile;
    }

    public int getPhoto_trophy() {
        return photo_trophy;
    }

    public void setPhoto_trophy(int photo_trophy) {
        this.photo_trophy = photo_trophy;
    }

    public String getPlyr_trophy() {
        return plyr_trophy;
    }

    public void setPlyr_trophy(String plyr_trophy) {
        this.plyr_trophy = plyr_trophy;
    }

    public items(String sr_no, String plyr_name, int photo_profile, int photo_trophy, String plyr_trophy) {
        this.sr_no = sr_no;
        this.plyr_name = plyr_name;
        this.plyr_trophy = plyr_trophy;
        this.photo_trophy = photo_trophy;
        this.photo_profile = photo_profile;
    }
}
