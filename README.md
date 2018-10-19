## Retrofit

### Retrofit请求过程

### Retrofit转换工厂有哪些


### Retrofit回调在哪个线程，OkHttp的回调在哪个线程

* Retrofit回调Executor在Retrofit.Builder的callbackExecutor配置，默认是主线程
* OkHttp回调Executor在Okhttp3框架内部的Dispatcher类中

### Class对象

```kotlin
    // 方法返回类型
    val returnType = method.getGenericReturnType()

    ParameterizedType
    GenericArrayType
    TypeVariable
    WildcardType


```

