package gr.mobap.mps;

/**
 * Created by kanag on 09/Μαρ/2016.
 */
public class MpsData {

    //    public String opsId;
    public String epitheto;
    public String onoma;
    //    public String rank;
    public String titlos;
    public String govPosition;
    //    public String emfanisi;
    public String onomaPatros;
    //    public String diatelesantes;
    public String komma;
    public String perifereia;
    public String birth;
    public String family;
    public String epaggelma;
    public String parliamentActivities;
    public String socialActivities;
    public String spoudes;
    public String languages;
    public String address;
    public String type;
    public String photo;
    public String site;
    public String email;
    //    public String legacyId;
//    public String onomasiaParty;
//    public String onomasiaPerifereias;
//    public String friendlyName;
//    public String electedIn;
//    public String sex;
    public String committees;

    public MpsData() {
        super();
    }

    public MpsData(String epitheto, String onoma, String titlos, String govPosition, String onomaPatros, String komma,
                   String perifereia, String birth, String family, String epaggelma, String parliamentActivities, String socialActivities,
                   String spoudes, String languages, String address, String type, String photo, String site, String email, String committees
    ) {
        super();
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
}