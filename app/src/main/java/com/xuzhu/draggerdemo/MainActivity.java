package com.xuzhu.draggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    // 带有此注解的属性或构造方法将参与到依赖注入中，
    // Dagger2会实例化有此注解的类
    @Inject
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //通过new MainModule(this)将view传递到MainModule里，
        // 然后MainModule里的provideMainView()方法返回这个View，
        // 当去实例化MainPresenter时，发现构造函数有个参数，
        // 此时会在Module里查找提供这个依赖的方法，将该View传递进去，这样就完成了presenter里View的注入


        DaggerMainModule_MainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        //调用Presenter方法加载数据
        mainPresenter.loadData();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.loadData();
            }
        });
    }

    @Override
    public void updateUI() {
        Log.d("View","updateUI");
    }
}
