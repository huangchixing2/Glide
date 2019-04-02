package com.example.admin.glide;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;


/**
 *
 *  图片加载框架glide学习
 *  https://blog.csdn.net/qq_31679853/article/details/78616289
 *  android:usesCleartextTraffic="true"    P版本后需要在权限清单中加入，否则无法加载图片
 *  android9.0系统默认禁止http协议，即禁止明文传输，必须使用https来通讯；而app中所使用的图片和某些地方用的正好是http协议的方式。
 */
public class MainActivity extends AppCompatActivity {

    Button loadImageBt;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImageBt = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image_view);

        loadImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    public void loadImage(){
        String url = "https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        String url_gif = "http://p1.pstatp.com/large/166200019850062839d3";  //动态图
        //Glide.with()方法用于创建一个加载图片的实例
        //with()方法可以接收Context、Activity或者Fragment类型的参数。也就是说我们选择的范围非常广，不管是在Activity还是Fragment中调用with()方法，都可以直接传this。那如果调用的地方既不在Activity中也不在Fragment中呢？也没关系，我们可以获取当前应用程序的ApplicationContext，传入到with()方法当中。注意with()方法中传入的实例会决定Glide加载图片的生命周期，如果传入的是Activity或者Fragment的实例，那么当这个Activity或Fragment被销毁的时候，图片加载也会停止。如果传入的是ApplicationContext，那么只有当应用程序被杀掉的时候，图片加载才会停止
//        load()方法，这个方法用于指定待加载的图片资源
//        // 加载本地图片
//        File file = new File(getExternalCacheDir() + "/image.jpg");
//        Glide.with(this).load(file).into(imageView);
//
//// 加载应用资源
//        int resource = R.drawable.image;
//        Glide.with(this).load(resource).into(imageView);
//
//// 加载二进制流
//        byte[] image = getImageBytes();
//        Glide.with(this).load(image).into(imageView);
//
//// 加载Uri对象
//        Uri imageUri = getImageUri();
//        Glide.with(this).load(imageUri).into(imageView);
//        into()方法，这个方法就很简单了，我们希望让图片显示在哪个ImageView上，把这个ImageView的实例传进去就可以了
        Glide.with(this)
//                .asBitmap()  //调用了asBitmap()方法，,只加载静态图片，现在GIF图就无法正常播放了，而是会在界面上显示第一帧的图片
//                .asGif()  //只加载动态图,如果指定了只能加载动态图片，而传入的图片却是一张静图的话，那么结果自然就只有加载失败
                .load(url)  //还可以加载动态图
                .placeholder(R.drawable.loading) //加载占位图-占位图就是指在图片的加载过程中，我们先显示一张临时的图片，等图片加载出来了再替换成要加载的图片
                .error(R.drawable.error)  //网络不好时候显示的图片
                .diskCacheStrategy(DiskCacheStrategy.NONE)   //传入DiskCacheStrategy.NONE参数，这样就可以禁用掉Glide的缓存功能
                .override(800,800) //   指定了一个图片的尺寸,Glide现在只会将图片加载成100*100像素的尺寸，而不会管你的ImageView的大小是多少了
                .fitCenter()
                .into(imageView);
    }


}
