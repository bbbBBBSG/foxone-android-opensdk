# foxone-android-opensdk
> Fox.ONE opensdk for android

## 接入

* 依赖

```
implementation 'com.fox.one:opensdk:0.0.1'
```

* 初始化

> 在Application#onCreate()初始化FoxOneOpenSDK
 
```
FoxOneOpenSDK.init(Context)
```

* API接口使用

> openSDK初始化后就可以调用业务API接口了，参考[API说明](#API说明)，例如：

```
//调用login接口
APILoader.load(AccountAPI::class.java).login(LoginRequestBody(tel, null, password ?: ""))
                .enqueue(object : Callback<AccountResponse> {
                    override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                        //onFailure
                    }

                    override fun onResponse(call: Call<AccountResponse>, response: Response<AccountResponse>) {
                        //onSuccess
                    }
                })
```

## 业务功能
* 账号模块：支持用户注册登录，修改用户信息，修改密码等功能
* 钱包模块：支持用户转入转出加密数字货币
* 红包模块：支持用户发红包功能

## API说明

* AccountAPI

|接口|参数|Response|说明|
|---|---|---|---|
|login|LoginRequestBody||登录|
|loginWithSMS|ValidatedRequest.ForLogin||短信验证码登录|
|register|RegisterRequestBody||注册|
|getAccountDetail|||获取登录用户详情|
|modify|ModifyAccountRequest||修改用户信息|
|resetPassword|ResetPasswordRequestBody||重置密码|
|bind|ValidatedRequest.ForChangeTelOrEmail||绑定用户手机号或者邮箱|
|logout|||登出|

* WalletAPI

|接口|参数|Response|说明|
|---|---|---|---|
|getAllWalletInfo|||获取所有钱包信息|
|getWalletInfo|assetID||获取指定钱包信息|
|getAllSnapshots|||获取交易记录|
|withDraw|pingToken<br>WithDrawRequest||转账|
|getWalletDepositCoins|||获取钱包支持的币种|
|getFee|pinToken<br>assetId<br>publicKey<br>label||获取转账费用|

* LuckyCoinAPI

* PinAPI

## 混淆配置

```
# --------for retrofit2 start------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembernames interface * {
        @retrofit.http.* <methods>;
}


#----------for retrofit2 end---------------

##--- Begin:GSON ----
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.

# For using GSON @Expose annotation

-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# keep enum so gson can deserialize it
-keepclassmembers enum * { *; }

##--- End:GSON ----

-keep class * implements java.io.Serializable{*;}
-keepnames class * implements java.io.Serializable
-keepclasseswithmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
```

## [CHANGELOG](CHANGELOG.md)

### [LICENSE](LICENSE)