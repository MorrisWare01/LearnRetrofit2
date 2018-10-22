## Retrofit

### Retrofit请求过程(Retrofit:2.4.0)

1. 通过Retrofit.Builder构造Service接口后，会创建一个实现了Service接口的动态代理类Proxy
1. 调用proxy的方法（非默认方法和Object方法）将触发动态代理类的InvocationHandler.invoke(Object, Method, Object[])方法
1. InvocationHandler.invoke方法会先判断ServiceMethod方法是否已经被构造，是的话直接下一步，
否则调用ServiceMethod.Builder(Retrofit, method).build()构造ServiceMethod
1. ServiceMethod构造返回后将继续构造OkHttpCall(ServiceMethod, args)
1. 最终调用serviceMethod.adapt(OkHttpCall)返回callAdapter转换的对象

#### ServiceMethod.Builder构造过程

1. 根据Method按添加顺序遍历Retrofit.callAdapterFactories获取不为空的CallAdapter
1. 根据Method按添加顺序遍历Retrofit.convertFactories获取Convert.Factory.responseBodyConverter()不为空的Convert。
1. 遍历Method的注解获取Http请求地址、请求头和请求参数
1. 遍历Method Parameter的注解结合Retrofit.stringConverter()和Retrofit.requestBodyConverter()转换器获取ParameterHandler[]数组

#### ServiceMethod.adapter(OkHttpCall)执行过程

1. 使用serviceMethod获取的callAdapter修饰OkHttpCall返回给业务层调用

#### OkHttpCall调用过程

1. 调用call.enqueue()/call.execute()时，会调用serviceMethod.toCall(Object[])方法将serviceMethod获取的http参数和
将ParameterHandler数组和Method参数解析出来的http请求参数构成成一个okhttp3.call请求
1. 当调用okhttp3.call请求获取到responseBody后，会调用serviceMethod.toResponse(responseBody)，
使用callAdapter将responseBody转化成callAdapter的ResponseType对象

### Retrofit转换工厂有哪些

* Retrofit的转换工厂Convert.Factor有3种转换类型
* stringConverter:负责将方法参数的类型转化为String类型,用于除了@Part、@PartMap、@Body、@RawPart的参数注解
* requestBodyConverter:负责将方法参数的类型转化为RequestBody类型，用于@Part、@PartMap、@Body、@RawPart的参数注解
* responseBodyConverter:负责将网络请求结束获取的responseBody转化成指定的实体对象

### Retrofit回调在哪个线程，OkHttp的回调在哪个线程

* Retrofit回调Executor在Retrofit.Builder的callbackExecutor配置，默认是主线程
* OkHttp回调Executor在Okhttp3框架内部的Dispatcher类中

### Method

```kotlin
    // 获取方法的注解数组
    val methodAnnotations: Array<Type> = method.getAnnotations()
    // 获取方法的参数类型数组
    val methodParameterTypes: Array<Type> = method.getGenericParameterTypes()
    // 获取方法的参数注解数组
    val methodParameterAnnotations: Array<Array<Type>> = method.getParameterAnnotations()
    // 获取方法的返回类型
    val returnType: Type = method.getGenericReturnType()

```

