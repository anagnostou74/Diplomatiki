package gr.mobap.mps;

/**
 * Created by kanag on 25/Μαρ/2016.
 */
public class Mps {

    public String rank = "rank";
    public String epitheto = "epitheto";
    public String onoma = "onoma";
    public String onomaPatros = "onomaPatros";
    public String titlos = "titlos";
    public String govPosition = "govPosition";
    public String komma = "komma";
    public String perifereia = "perifereia";
    public String birth = "birth";
    public String family = "family";
    public String epaggelma = "epaggelma";
    public String parliamentActivities = "parliamentActivities";
    public String socialActivities = "socialActivities";
    public String spoudes = "spoudes";
    public String languages = "languages";
    public String address = "address";
    public String site = "site";
    public String email = "email";

    public String getRank() {
        return rank;
    }

    public String getEpitheto() {
        return epitheto;
    }

    public String getOnoma() {
        return onoma;
    }

    public String getOnomaPatros() {
        return onomaPatros;
    }

    public String getTitlos() {
        return titlos;
    }

    public String getGovPosition() {
        return govPosition;
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

    public String getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }


    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setEpitheto(String epitheto) {
        this.epitheto = epitheto;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setOnomaPatros(String onomaPatros) {
        this.onomaPatros = onomaPatros;
    }

    public void setTitlos(String titlos) {
        this.titlos = titlos;
    }

    public void setGovPosition(String govPosition) {
        this.govPosition = govPosition;
    }

    public void setKomma(String komma) {
        this.komma = komma;
    }

    public void setPerifereia(String perifereia) {
        this.perifereia = perifereia;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setEpaggelma(String epaggelma) {
        this.epaggelma = epaggelma;
    }

    public void setParliamentActivities(String parliamentActivities) {
        this.parliamentActivities = parliamentActivities;
    }

    public void setSocialActivities(String socialActivities) {
        this.socialActivities = socialActivities;
    }

    public void setSpoudes(String spoudes) {
        this.spoudes = spoudes;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //@Override
    public String toString() {
        return epitheto + " " + onoma + "\n" + "του " + onomaPatros + "\n" + titlos + "\n" + "\n" + govPosition + "\n" + site+ "\n" + email;
    }

}
