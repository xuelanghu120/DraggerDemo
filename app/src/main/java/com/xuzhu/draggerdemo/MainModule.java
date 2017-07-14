package com.xuzhu.draggerdemo;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 56417 on 2017/7/14.
 * MainModlue是一个注解类
 * 用@Module注解标注，主要用来提供依赖
 *
 * 之所以有Module类主要是为了提供那些没有构造函数的类的依赖，这些类无法用@Inject标注，比如第三方类库，系统类，以及上面示例的View接口。
 */

//带有此注解的类，用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法，
// 这些方法就是所提供的依赖，Dagger2会在该类中寻找实例化某个类所需要的依赖。
@Module
public class MainModule {


    //声明了MainContract.View成员属性
    private final MainContract.View mView;

    //在构造方法里将外界传进来的view赋值给mView，并通过一个@Provides标注的以provide开头的方法，将这个view返回，这个以provide开头的方法就是提供依赖的，
    public MainModule(MainContract.View view) {
        mView = view;
    }

    //通过一个@Provides标注的以provide开头的方法，将这个view返回，这个以provide开头的方法就是提供依赖的
    @Provides
    MainContract.View provideMainView() {
        return mView;
    }


    //@Component 用来将@Inject和@Module联系起来的桥梁，
    // 从@Module中获取依赖并将依赖注入给@Inject
    @Component(modules = MainModule.class)
    public interface MainComponent {
        void inject(MainActivity activity);
    }
}
