package gr.mobap.mps;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class XMLPullParserHandler {
    List<Mps> mps;
    private Mps mp;
    private String text;

    public XMLPullParserHandler() {
        mps = new ArrayList<Mps>();
    }

    public List<Mps> getMps() {
        return mps;
    }

    public List<Mps> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("mp")) {
                            // create a new instance of mp
                            mp = new Mps();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("mp")) {
                            // add mp object to list
                            mps.add(mp);
                        } else if (tagname.equalsIgnoreCase("rank")) {
                            mp.setRank(text);
                        } else if (tagname.equalsIgnoreCase("epitheto")) {
                            mp.setEpitheto(text);
                        } else if (tagname.equalsIgnoreCase("onoma")) {
                            mp.setOnoma(text);
                        } else if (tagname.equalsIgnoreCase("onomaPatros")) {
                            mp.setOnomaPatros(text);
                        } else if (tagname.equalsIgnoreCase("titlos")) {
                            mp.setTitlos(text);
                        } else if (tagname.equalsIgnoreCase("govPosition")) {
                            mp.setGovPosition(text);
                        } else if (tagname.equalsIgnoreCase("komma")) {
                            mp.setKomma(text);
                        } else if (tagname.equalsIgnoreCase("perifereia")) {
                            mp.setPerifereia(text);
                        } else if (tagname.equalsIgnoreCase("birth")) {
                            mp.setBirth(text);
                        } else if (tagname.equalsIgnoreCase("family")) {
                            mp.setFamily(text);
                        } else if (tagname.equalsIgnoreCase("epaggelma")) {
                            mp.setEpaggelma(text);
                        } else if (tagname.equalsIgnoreCase("parliamentActivities")) {
                            mp.setParliamentActivities(text);
                        } else if (tagname.equalsIgnoreCase("socialActivities")) {
                            mp.setSocialActivities(text);
                        } else if (tagname.equalsIgnoreCase("spoudes")) {
                            mp.setSpoudes(text);
                        } else if (tagname.equalsIgnoreCase("languages")) {
                            mp.setLanguages(text);
                        } else if (tagname.equalsIgnoreCase("address")) {
                            mp.setAddress(text);
                        } else if (tagname.equalsIgnoreCase("site")) {
                            mp.setSite(text);
                        } else if (tagname.equalsIgnoreCase("email")) {
                            mp.setEmail(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mps;
    }
}