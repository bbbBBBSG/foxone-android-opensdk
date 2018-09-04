# foxone-android-opensdk
> Fox.ONE opensdk for android

## 接入

* 依赖

```
implementation 'com.fox.one:opensdk:0.0.2'
```

* 初始化

> 在Application#onCreate()初始化FoxOneOpenSDK
 
```
FoxOneOpenSDK.init(Context)
```
* 开启Debug模式

```
FoxOneOpenSDK.debugEnable(Boolean)
```

* 设置Token

```
//在用户登录状态下设置token, token用于API接口请求使用，无token或者token失效时，API接口会返回ResponseCode 401错误码（身份验证失败）
FoxOneOpenSDK.token = '34tsr45trert4645643t'
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

**`注意：API的具体使用情况可参考Demo`**
## 业务功能
* 账号模块：支持用户注册登录，修改用户信息，修改密码等功能
* 钱包模块：支持用户转入转出加密数字货币
* 红包模块：支持用户发红包功能

## API说明

* AccountAPI

|接口|参数|Response|说明|
|---|---|---|---|
|login|LoginRequest||登录|
|loginWithSMS|ValidatedRequest.ForLogin||短信验证码登录|
|register|RegisterRequest||注册|
|getAccountDetail|||获取登录用户详情|
|modify|ModifyAccountRequest||修改用户信息|
|resetPassword|ResetPasswordRequest||重置密码|
|bind|ValidatedRequest.ForChangeTelOrEmail||绑定用户手机号或者邮箱|
|logout|||登出|

* WalletAPI

|接口|参数|Response|说明|
|---|---|---|---|
|getAllWalletInfo|||获取所有钱包信息|
|getWalletInfo|assetID||获取指定钱包信息|
|getAllSnapshots|||获取交易记录|
|withDraw|pin<br>WithDrawRequest||转账|
|getWalletDepositCoins|||获取钱包支持的币种|
|getFee|pin<br>assetId<br>publicKey<br>label||获取转账费用|

* LuckyCoinAPI

|接口|参数|Response|说明|
|---|---|---|---|
|create|LuckyCoinRequest||创建新红包|
|push|pin：<br>luckyCoinId：||发布红包|

* PinAPI

|接口|参数|Response|说明|
|---|---|---|---|
|setPin|PinRequest||设置用户的pin|
|modifyPin|pin:<br>PinRequest:||修改用户pin码|
|verifyPin|pin:<br>PinRequest:||验证用户的pin码|

* 短信验证码API：`SMSCodeHelper`
> 通过该接口请求相应业务的短信验证码，包括如下业务

```
//激活验证码
SMSCodeType.ACTIVATION,
//重置密码验证码
SMSCodeType.RESET_PASSWORD,
//绑定账号验证码
SMSCodeType.BIND,
//短信登录验证码
SMSCodeType.LOGIN

```

接口：

|接口|参数|Response|说明|
|---|---|---|---|
|requestSMSCode|tel:<br>email:<br>captcha:图片验证码<br>smsCodeType:业务类型||请求短信验证码|


## 通用错误码
> 通用错误码指HTTP响应码(ResponseCode)，[参考](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status)

|错误码|说明|
|---|---|
|401|无token或token失效|
|||

## 业务错误码
服务端返回的业务数据结构如下：

```
{
  "code": 0,
  "msg": "",
  "data": {}
}
```
当`code`为0时说明业务数据返回成功，当`code`不为0时说明业务数据返回失败，对应的错误码如下：

```
public class ResponseErrorCode {
    /**
     * 操作错误
     */
    public static final int INVALID_OPERATION_MSG = 1;
    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR_MSG = 2;
    /**
     * 服务器错误
     */
    public static final int SERVER_FALUT_MSG = 3;
    /**
     * 访问太频繁
     */
    public static final int VISIT_TOO_FREQUENTLY_MSG = 4;
    /**
     * 用户登录太频繁
     */
    public static final int USER_LOGIN_TOO_FREQUENTLY_MSG = 5;
    /**
     * 禁止访问
     */
    public static final int FORBIDDEN_MSG = 6;
    /**
     * 无效用户
     */
    public static final int INVALID_USER_MSG = 1538;
    /**
     * 用户未登录
     */
    public static final int USER_NOT_LOGGED_IN_MSG = 1537;
    /**
     * 登录失败
     */
    public static final int USER_LOGIN_FAILED_MSG = 1539;
    /**
     * 空邮箱地址
     */
    public static final int EMAIL_EMPTY_MSG = 1545;
    /**
     * 无效的邮箱地址
     */
    public static final int EMAIL_INVALID_MSG = 1546;
    /**
     * 注册邮箱已存在
     */
    public static final int EMAIL_ALREADY_EXISTS_MSG = 1547;
    /**
     * 密码太短
     */
    public static final int PASSWORD_TOO_SHORT_MSG = 1548;
    /**
     * 密码太长
     */
    public static final int PASSWORD_TOO_LONG_MSG = 1549;
    /**
     * 密码为空
     */
    public static final int PASSWORD_EMPTY_MSG = 259;
    /**
     * 昵称太长或太短
     */
    public static final int NAME_LENGTH_INVALID = 1551;
    /**
     * 没有设置pin码
     */
    public static final int PIN_NOT_SET_MSG = 1553;
    /**
     * pin码错误
     */
    public static final int INCORRECT_PIN_MSG = 1554;
    /**
     * 手机号为空
     */
    public static final int TEL_NUMBER_EMPTY_MSG = 1568;
    /**
     * 非法的手机号
     */
    public static final int TEL_NUMBER_INVALID_MSG = 1569;
    /**
     * 手机号已经存在(已废弃)
     */
    @Deprecated
    public static final int TEL_NUMBER_ALREADY_EXISTS_MSG = 1547;
    /**
     * 手机号已存在
     */
    public static final int TEL_NUMBER_ALREADY_EXISTS_MSG_NEW = 1544;
    /**
     * 验证码为空
     */
    public static final int VALIDATION_CODE_EMPTY_MSG = 1566;
    /**
     * 验证码错误
     */
    public static final int VALIDATION_CODE_ERROR_MSG = 1557;
    /**
     * 用户未激活
     */
    public static final int USER_NOT_ACTIVATED_MSG = 1555;
    /**
     * 用户已激活
     */
    public static final int USER_ALREADY_ACTIVATED_MSG = 1556;
    /**
     * 当前密码错误
     */
    public static final int CURRENT_PASSWORD_WRONG_MSG = 1558;
    /**
     * 当前密码为空
     */
    public static final int CURRENT_PASSWORD_EMPTY_MSG = 1559;

    public static final int MIXIN_BIND_WITH_OTHER_USER_MSG = 1560;

    public static final int MIXIN_NOT_BIND_WITH_USER_MSG = 1561;
    /**
     * 图片验证码错误
     */
    public static final int CAPTCH_CODE_ERROR_MSG = 1584;
    /**
     * 发送验证码错误
     */
    public static final int SEND_VALIDATION_FAILED_ERROR_MSG = 1585;
    public static final int ITEM_NOT_EXIST_ERROR_MSG = 1586;
    public static final int NO_DATA_CHANGED_ERROR_MSG = 1587;
    /**
     * 已绑定其他账号
     */
    public static final int ALREADY_BINDED_TO_ANOTHER_USER_ERROR_MSG = 1588;
    /**
     * 无效的公钥
     */
    public static final int INVALID_PUBLIC_KEY = 1601;

    public static final int INVALID_APPLICATION_MSG = 2048;

    //wallet error code
    /**
     * 无效的trace id
     */
    public static final int WALLET_INVALID_TRACE_ID_ERROR_NO = 1602;
    /**
     * 钱包余额不足
     */
    public static final int WALLET_AMOUNT_TOO_SMALL_NO = 1603;
    /**
     * 钱包余额不足
     */
    public static final int WALLET_INSUFFICIENT_BALANCE_NO = 1604;

    //lucky packet
    /**
     * 红包过期
     */
    public static final int LUCKY_MONEY_EXPIRED_PACKET = 2001;
    /**
     * 空的红包
     */
    public static final int LUCKY_MONEY_EMPTY_PACKET = 2002;
    /**
     * 红包支付失败
     */
    public static final int LUCKY_MONEY_PAY_PACKET_FAILED = 2003;
    /**
     * 红包未支付
     */
    public static final int LUCKY_MONEY_PACKET_UNPAID = 2004;
    /**
     * 余额无效
     */
    public static final int LUCKY_MONEY_INVALID_AMOUNT = 2005;
    /**
     * 余额不足
     */
    public static final int LUCKY_MONEY_AMOUNT_TOO_SMALL = 2006;
}

```


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