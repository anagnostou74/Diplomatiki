package gr.sextreme.images;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import gr.sextreme.R;

public class Image extends AppCompatActivity {

    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data = new ArrayList<>();

    public static final String[] titles = new String[]{"Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος",
            "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος"};
    public static String IMGS[] = {
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        for (int i = 0; i < IMGS.length; i++) {

            ImageModel imageModel = new ImageModel();
            imageModel.setName("Φωτογραφία " + i);
            imageModel.setUrl(IMGS[i]);
            data.add(imageModel);

        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new GalleryAdapter(Image.this, data);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(Image.this, DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", data);
                        intent.putExtra("pos", position);
                        startActivity(intent);

                    }
                }));

    }

}