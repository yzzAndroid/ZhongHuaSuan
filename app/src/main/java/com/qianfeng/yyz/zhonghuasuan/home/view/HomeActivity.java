package com.qianfeng.yyz.zhonghuasuan.home.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.apublic.BaseActivity;
import com.qianfeng.yyz.zhonghuasuan.mine.view.MineFragment;
import com.qianfeng.yyz.zhonghuasuan.newest.view.NewestFragment;
import com.qianfeng.yyz.zhonghuasuan.search.view.SearchFragmentFragment;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements IHomeActivity {
    @BindView(R.id.home_container)
    RelativeLayout mHomeContainer;
    @BindView(R.id.home_bottom)
    RadioGroup mRadioGroup;
    @BindView(R.id.home_rb)
    RadioButton home_rb;
    @BindView(R.id.newest_rb)
    RadioButton newest_rb;
    @BindView(R.id.search_rb)
    RadioButton search_rb;
    @BindView(R.id.mine_rb)
    RadioButton mine_rb;

    private RadioButton mCurrentRadioButton;
    private Fragment mCurrentFragment;
    private Fragment mHomeFragment;
    private Fragment mNewestFragment;
    private Fragment mSearchFragment;
    private MineFragment mMineFragment;
    private Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initContainer();
        setSwipeBackEnable(false);
    }

    @Override
    public void initContainer() {
        mHomeFragment = new HomeFragment();
        mNewestFragment = new NewestFragment();
        mSearchFragment = new SearchFragmentFragment();
        mMineFragment = new MineFragment();
        //初始化
        //首先要将home加到容器
        getSupportFragmentManager().beginTransaction().add(R.id.home_container, mHomeFragment).commit();
        //设置初始值
        mCurrentRadioButton = home_rb;
        mCurrentFragment = mHomeFragment;
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_rb:
                        changeColor(home_rb);
                        changeFragment(mHomeFragment);
                        break;
                    case R.id.newest_rb:
                        changeColor(newest_rb);
                        changeFragment(mNewestFragment);
                        break;
                    case R.id.search_rb:
                        changeColor(search_rb);
                        changeFragment(mSearchFragment);
                        break;
                    case R.id.mine_rb:
                        changeColor(mine_rb);
                        changeFragment(mMineFragment);
                        break;
                }
            }
        });
    }

    //控制颜色
    public void changeColor(RadioButton now) {
        now.setTextColor(getResources().getColor(R.color.point_select));
        mCurrentRadioButton.setTextColor(getResources().getColor(R.color.default_color));
        mCurrentRadioButton = now;
    }

    //控制碎片切换
    public void changeFragment(Fragment now) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mCurrentFragment);
        if (!now.isAdded()) {
            transaction.add(R.id.home_container, now);
        }
        transaction.show(now);
        transaction.commit();
        mCurrentFragment = now;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("======", "===========" + requestCode);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    if (null != data) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        mMineFragment.setImage(bitmap);
                        Log.e("b", "b" + bitmap);
                    }
                }
                break;
            case 200:
                if (resultCode == RESULT_OK) {

                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mMineFragment.getImagePath(), "image/*");
                    intent.putExtra("crop", "true");//可裁剪
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 600);
                    intent.putExtra("outputY", 600);
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mMineFragment.getImagePath());
                    intent.putExtra("return-data", false);//若为false则表示不返回数据
                    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    intent.putExtra("noFaceDetection", true);
                    startActivityForResult(intent, 300);

//
                }
                break;
            case 300:
                Bitmap bitmap = BitmapFactory.decodeFile(mMineFragment.getImagePath().getPath());
                mMineFragment.setImage(bitmap);
                mMineFragment.conmitPath();
                break;


            case 101:
                Log.e("====","==="+data.getData());

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 300);
                intent.putExtra("outputY", 300);
                intent.putExtra("scale", true);
                intent.putExtra("return-data",true);
                intent.setData(data.getData());
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
                    startActivityForResult(intent, 101);
                }else {
                    File root = Environment.getExternalStorageDirectory();
                    File zhs = new File(root,"zhs");
                    if (!zhs.exists()){
                        zhs.mkdirs();
                    }
                    File path = new File(zhs,System.currentTimeMillis()+".jpg");
                    imagePath = Uri.fromFile(path);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imagePath);
                    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    //不启用人脸识别
                    intent.putExtra("noFaceDetection", false);
                    startActivityForResult(intent,102);
                }
                break;

            case 102:
                Bitmap bitmap3 = BitmapFactory.decodeFile(imagePath.getPath());
                mMineFragment.setImage(bitmap3);
                mMineFragment.conmitPath(imagePath.getPath());
                break;
        }
    }
}
