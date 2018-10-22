## Retrofit

### Retrofit请求过程

1. 通过Retrofit.Builder构造Service接口后，会创建一个实现了Service接口的动态代理类Proxy
1. 调用proxy的方法（非默认方法和Object方法）将触发动态代理类的InvocationHandler.invoke(Object, Method, Object[])方法
1. invoke方法会调用ServiceMethod.parseAnnotations(Retrofit, Method)解析Method对象
1. parseAnnotations方法将调用RequestFactory.Builder构造器构造RequestFactory对象
1. RequestFactory.Builder.build()中先遍历Method的注解获取Http请求地址、请求头和请求参数，然后再遍历Method Parameter的注解
结合Retrofit.stringConverter(Type, Annotation[], Retrofit)和Retrofit.requestConverter(Type, Annotation[], Retrofit)转换器
获取ParameterHandler[]数组
1. RequestFactory构造完成后，执行HttpServiceMethod.parseAnnotations(Retrofit, Method, RequestFactory)构造HttpServiceMethod
1. HttpServiceMethod.parseAnnotations方法会根据Method按添加顺序遍历Retrofit.callAdapterFactories获取CallAdapter，
按添加顺序遍历Retrofit.convertFactories获取Convert.Factory.responseBodyConverter()不为空的Convert。
1. HttpServiceMethod.invoke()先构造OkHttpCall(RequestFactory, Object[], okhttp3.Call.Factory, Converter),
再使用callAdapter.adapt(OkHttpCall)返回最终执行对象

### Retrofit转换工厂有哪些


### Retrofit回调在哪个线程，OkHttp的回调在哪个线程

* Retrofit回调Executor在Retrofit.Builder的callbackExecutor配置，默认是主线程
* OkHttp回调Executor在Okhttp3框架内部的Dispatcher类中

### Class对象

```kotlin
    // 方法的注解
    val methodAnnotations: Array<Type> = method.getAnnotations()
    // 方法的参数类型
    val methodParameterTypes: Array<Type> = method.getGenericParameterTypes()


    // 方法返回类型
    val returnType: Type = method.getGenericReturnType()

    ParameterizedType
    GenericArrayType
    TypeVariable
    WildcardType


```

