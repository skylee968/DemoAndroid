package studio.orange.mobile.reader.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.activities.BookCategoryActivity;
import studio.orange.mobile.reader.adapters.ListViewCategoryAdapter;
import studio.orange.mobile.reader.configs.AppConfig;
import studio.orange.mobile.reader.entity.CategoryEntity;
import studio.orange.mobile.reader.https.RestClient;
import studio.orange.mobile.reader.entityresult.CategoryResultEntity;
import studio.orange.mobile.reader.uitls.BookUtils;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 8/2/2015.
 */
public class CategoryFragment extends BaseFragment implements OnItemClickListener {
//    private GridView mListView;
//    private GridViewCategoryAdapter mAdapter;

    private ListView mLisView;
    private ListViewCategoryAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_category, container, false);
            initView();
            initListener();
        } else if(mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        return mView;
    }
    private void initView() {
//        mListView       = (GridView) mView.findViewById(R.id.gridview_category);
//        mAdapter        = new GridViewCategoryAdapter(getActivity());
//        mListView.setAdapter(mAdapter);
        mLisView        = (ListView) mView.findViewById(R.id.listview_category);
        mAdapter        = new ListViewCategoryAdapter(getActivity());
        mLisView.setAdapter(mAdapter);
        loadCategory();
    }
    private void initListener() {
//        mListView.setOnItemClickListener(this);
        mLisView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CategoryEntity category = (CategoryEntity) mAdapter.getItem(i);
        if(category != null) {
            String data = BookUtils.convertObjectToJson(category);
            if(data != null) {
                Intent intent = new Intent(getActivity(), BookCategoryActivity.class);
                intent.putExtra(AppConfig.BUNDLE_KEY.CATEGORY, data);
                getActivity().startActivity(intent);
            }
        }
    }
    private void loadCategory() {
        RestClient.get().getCategory(new Callback<CategoryResultEntity>() {
            @Override
            public void success(CategoryResultEntity categoryResultEntity, Response response) {
                if(categoryResultEntity != null && categoryResultEntity.data != null && categoryResultEntity.data.size() > 0) {
                    mAdapter.updateListData(categoryResultEntity.data);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
