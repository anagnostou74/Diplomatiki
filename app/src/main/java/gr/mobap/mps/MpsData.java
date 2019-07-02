package gr.mobap.mps;

public class MpsData {
    public String rank;
    public String epitheto;
    public String onoma;
    public String onomaPatros;
    public String titlos;
    public String govPosition;
    public String image;
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
    public String site;
    public String email;
    public String phone;
    public String perifereia_komma;

    public MpsData() {

    }

    public MpsData(String rank,
                   String epitheto,
                   String onoma,
                   String onomaPatros,
                   String titlos,
                   String govPosition,
                   String image,
                   String komma,
                   String perifereia,
                   String birth,
                   String family,
                   String epaggelma,
                   String parliamentActivities,
                   String socialActivities,
                   String spoudes,
                   String languages,
                   String address,
                   String site,
                   String email,
                   String phone,
                   String perifereiaKomma) {


        this.epitheto = epitheto;
        this.onoma = onoma;
        this.onomaPatros = onomaPatros;
        this.titlos = titlos;
        this.govPosition = govPosition;
        this.image = image;
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
        this.site = site;
        this.email = email;
        this.phone = phone;
        this.perifereia_komma = perifereiaKomma;
    }
}