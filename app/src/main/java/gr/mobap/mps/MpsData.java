package gr.mobap.mps;

import java.util.ArrayList;

/**
 * Created by kanag on 09/Μαρ/2016.
 */
public class MpsData {

    //    private String opsId;
    private String epitheto;
    private String onoma;
    //    private String rank;
    private String titlos;
    private String govPosition;
    //    private String emfanisi;
    private String onomaPatros;
    //    private String diatelesantes;
    private String komma;
    private String perifereia;
    private String birth;
    private String family;
    private String epaggelma;
    private String parliamentActivities;
    private String socialActivities;
    private String spoudes;
    private String languages;
    private String address;
    private String type;
    private String photo;
    private String site;
    private String email;
    //    private String legacyId;
//    private String onomasiaParty;
//    private String onomasiaPerifereias;
//    private String friendlyName;
//    private String electedIn;
//    private String sex;
    public String committees;

    private MpsData() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public MpsData(String epitheto, String onoma, String titlos, String govPosition, String onomaPatros, String komma,
            String perifereia, String birth, String family, String epaggelma, String parliamentActivities, String socialActivities,
            String spoudes, String languages, String address, String type, String photo, String site, String email, String committees) {
        this.epitheto = epitheto;
        this.onoma = onoma;
        this.titlos = titlos;
        this.govPosition = govPosition;
        this.onomaPatros = onomaPatros;
        this.komma = komma;
        this.perifereia = perifereia;
        this.birth = birth;
        this.family = family;
        this.epaggelma = epaggelma;
        this.parliamentActivities = parliamentActivities;
        this.socialActivities = socialActivities;
        this.spoudes = spoudes;
        this.languages = languages;
        this.address = address;
        this.type = type;
        this.photo = photo;
        this.site = site;
        this.email = email;
        this.committees = committees;
    }

    public String getEpitheto() {
        return epitheto;
    }

    public String getOnoma() {
        return onoma;
    }

    public String getTitlos() {
        return titlos;
    }

    public String getGovPosition() {
        return govPosition;
    }

    public String getOnomaPatros() {
        return onomaPatros;
    }

    public String getKomma() {
        return komma;
    }

    public String getPerifereia() {
        return perifereia;
    }

    public String getBirth() {
        return birth;
    }

    public String getFamily() {
        return family;
    }

    public String getEpaggelma() {
        return epaggelma;
    }

    public String getParliamentActivities() {
        return parliamentActivities;
    }

    public String getSocialActivities() {
        return socialActivities;
    }

    public String getSpoudes() {
        return spoudes;
    }

    public String getLanguages() {
        return languages;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

}