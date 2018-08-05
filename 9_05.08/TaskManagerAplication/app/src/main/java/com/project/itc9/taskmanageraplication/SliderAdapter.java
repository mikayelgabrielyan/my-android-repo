package com.project.itc9.taskmanageraplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.support.v4.view.PagerAdapter;

import java.util.List;

class SliderAdapter extends PagerAdapter {

    private List<SimpleSliderItem> sliderItemList;

    public SliderAdapter(List<SimpleSliderItem> list) {
        this.sliderItemList = list;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext())
                .inflate(R.layout.slider_activity, container, false);
        SimpleSliderItem simpleSliderItem = sliderItemList.get(position);
        ImageView img = itemView.findViewById(R.id.image);
        TextView tvText = itemView.findViewById(R.id.tv_text);
        img.setImageResource(simpleSliderItem.getImg());
        tvText.setText(simpleSliderItem.getText());
        if (simpleSliderItem.getLogoText() != null) {
            img.setMaxWidth(50);
            TextView tvLogoText = itemView.findViewById(R.id.tv_logo_text);
            tvLogoText.setText("Grapes");
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return sliderItemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}