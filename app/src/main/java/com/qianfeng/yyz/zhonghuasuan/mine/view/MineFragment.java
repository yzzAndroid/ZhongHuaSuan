package com.qianfeng.yyz.zhonghuasuan.mine.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApi;
import com.qianfeng.yyz.zhonghuasuan.apublic.MyApplication;
import com.qianfeng.yyz.zhonghuasuan.login.view.LoginActivity;
import com.qianfeng.yyz.zhonghuasuan.mine.presenter.MinePresenter;
import com.qianfeng.yyz.zhonghuasuan.welcome.model.CheckIsFirstUseAppImpl;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.DbUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements IMineView {

    @BindView(R.id.mine_default_header)
    CircleImageView head;
    @BindView(R.id.mine_head_text)
    TextView headTextView;
    @BindView(R.id.mine_rl_collect)
    RelativeLayout collection;
    @BindView(R.id.mine_rl_traces)
    RelativeLayout traces;


    private Uri imagePath;

    private boolean isLogin = false;
    private AlertDialog dialog;

    private Map<String,Object> map;

    public MineFragment() {
    }

    public Uri getImagePath() {
        return imagePath;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {

        //查询状态
       isLogin = MyApplication.getInstance().isLogin();

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin){
                    //相册找图片换上
                    showDialog();
                }else {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));

                }
            }
        });

        if (isLogin){
            headTextView.setText(MyApplication.getInstance().getUname());
            Picasso.with(getActivity()).load(MyApplication.getInstance().getAvatar()).placeholder(R.mipmap.personal_center_photo)
                    .into(head);
            Log.e("=======","===img=="+MyApplication.getInstance().getAvatar());
        }
        map = new HashMap<>();

        //收藏
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),CollectionActivity.class));
            }
        });
        //足迹
        traces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity(),MyTracesActivity.class));
            }
        });
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.take_image_dialog, null);
        builder.setView(linearLayout);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        TextView takePhoto = (TextView) linearLayout.findViewById(R.id.take_dialog_take_photo);
        TextView takeFromOs = (TextView) linearLayout.findViewById(R.id.take_dialog_take_os);
        TextView cancle = (TextView) linearLayout.findViewById(R.id.take_dialog_cancel);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startIntent(intent1);
                dialog.dismiss();
            }
        });

        takeFromOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                getActivity().startActivityForResult(intent,101);
                dialog.dismiss();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void startIntent(Intent intent) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
            getActivity().startActivityForResult(intent, 100);
        }else {
            File root = Environment.getExternalStorageDirectory();
            File zhs = new File(root,"zhs");
            if (!zhs.exists()){
                zhs.mkdirs();
            }
            File path = new File(zhs,System.currentTimeMillis()+".jpg");
            imagePath = Uri.fromFile(path);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imagePath);
            getActivity().startActivityForResult(intent,200);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.getInstance().isLogin()) {
            if (!isLogin){
                headTextView.setText(MyApplication.getInstance().getUname());
                Picasso.with(getActivity()).load(MyApplication.getInstance().getAvatar()).placeholder(R.mipmap.personal_center_photo)
                        .into(head);
                isLogin = true;
            }
        }
    }


    public void setImage(Bitmap bitmap){
        Log.e("=====","====setImage=====");
        head.setImageBitmap(bitmap);
    }


    public void conmitPath(){
        SharedPreferences p = getActivity().getSharedPreferences(CheckIsFirstUseAppImpl.USER_INFO, Context.MODE_PRIVATE);
        p.edit().putString(MyApi.AVATAR,imagePath.getPath()).commit();
        //上传服务器
       postImage(imagePath.getPath());
    }

    private void postImage(String path) {
        map.put(MyApi.UID,MyApplication.getInstance().getUid());
        map.put(MyApi.VERSION,"3003201");
        map.put(MyApi.CLIENT_TYPE,"3");
        map.put(MyApi.SIGN,MyApplication.getInstance().getSign());
        map.put("img",new File(path));
        MinePresenter.getInstance(this).postImg(map);

    }

    public void conmitPath(String imagePath){
        SharedPreferences p = getActivity().getSharedPreferences(CheckIsFirstUseAppImpl.USER_INFO, Context.MODE_PRIVATE);
        p.edit().putString(MyApi.AVATAR,imagePath).commit();
        //上传服务器
        postImage(imagePath);
    }


    @Override
    public void postImageSuccess(String msg) {
        Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postImageFailed(String msg) {
        Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postCollectionSuccess(String msg) {

    }

    @Override
    public void postCollectionFailed(String msg) {

    }

    @Override
    public void postFootsSuccess(String msg) {

    }

    @Override
    public void postFootsFailed(String msg) {

    }
}
