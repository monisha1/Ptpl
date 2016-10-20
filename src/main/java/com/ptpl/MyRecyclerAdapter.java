package com.ptpl;

/**
 * Created by MC11 on 5/23/2016.
 */
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int i) {
        final FeedItem feedItem = feedItemList.get(i);

        //Download image using picasso library
        Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(customViewHolder.imageView);

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        //Handle click event on both title and image click
        customViewHolder.textView.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);

        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);
        customViewHolder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //will show popup menu here
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, customViewHolder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                              /* Bitmap image = BitmapFactory.decodeResource(mContext.getResources(), Integer.parseInt(feedItem.getThumbnail()));
                                SharePhoto photo = new SharePhoto.Builder()
                                        .setBitmap(image)
                                        .setCaption(feedItem.getTitle())
                                        .build();
                                SharePhotoContent content = new SharePhotoContent.Builder()
                                        .addPhoto(photo)
                                        .build();
                                 ShareButton shareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
                                shareButton.setShareContent(content);*/
                                //try {
                                    Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                                    shareIntent.setType("text/html");
                                    shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test Mail");
                                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Launcher");
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, feedItem.getThumbnail());
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, feedItem.getTitle());
                                    shareIntent.setPackage("com.facebook.katana");
                                    mContext.startActivity(Intent.createChooser(shareIntent,"via"));
                               /* PackageManager packManager = mContext.getPackageManager();
                                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(shareIntent,  PackageManager.MATCH_DEFAULT_ONLY);

                                boolean resolved = false;
                                for(ResolveInfo resolveInfo: resolvedInfoList){
                                    if(resolveInfo.activityInfo.packageName.contains("com.facebook.katana")) {
                                        shareIntent.setClassName(
                                                resolveInfo.activityInfo.packageName,
                                                resolveInfo.activityInfo.name );
                                        resolved = true;
                                        break;
                                    }
                                }
                                if(resolved){
                                    mContext.startActivity(shareIntent);
                                }else{
                                    Toast.makeText(mContext, "Facebook App is not installed", Toast.LENGTH_LONG).show();
                                }
                        } catch (final ActivityNotFoundException e) {
                            Toast.makeText(mContext, "You don't seem to have Facebook App installed on this device", Toast.LENGTH_SHORT).show();
                        }
                             //  mContext.startActivity(shareIntent);//Intent.createChooser(shareIntent, "Share Deal"));
*/
                                break;

                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }



    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder. getAdapterPosition();

            FeedItem feedItem = feedItemList.get(position);
            Toast.makeText(mContext, feedItem.getTitle(), Toast.LENGTH_SHORT).show();
            MainListActivity.textu.setText(feedItem.getTitle());


        }
    };


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        public TextView buttonViewOption;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }


    private void sendShareFacebook() {

    }
    }

