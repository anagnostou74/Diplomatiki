package gr.mobap.ekdoseis;

public class EkdoseisData {
    public String url;
    public String img_url;
    public String type;
    public String text;

    public EkdoseisData() {
    }

    public EkdoseisData(String url,
                        String img_url,
                        String text,
                        String type) {

        this.url = url;
        this.img_url = img_url;
        this.text = text;
        this.type = type;

    }
}