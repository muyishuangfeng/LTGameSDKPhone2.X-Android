package com.sdk.ltgame.ltphone;

import android.app.Activity;
import android.content.Context;

import com.gentop.ltgame.ltgamesdkcore.common.LTGameOptions;
import com.gentop.ltgame.ltgamesdkcore.common.LTGameSdk;
import com.gentop.ltgame.ltgamesdkcore.common.Target;
import com.gentop.ltgame.ltgamesdkcore.impl.OnLoginStateListener;
import com.gentop.ltgame.ltgamesdkcore.impl.OnRechargeListener;
import com.gentop.ltgame.ltgamesdkcore.model.LoginObject;
import com.gentop.ltgame.ltgamesdkcore.model.RechargeObject;
import com.gentop.ltgame.ltgamesdkcore.platform.AbsPlatform;
import com.gentop.ltgame.ltgamesdkcore.platform.IPlatform;
import com.gentop.ltgame.ltgamesdkcore.platform.PlatformFactory;
import com.gentop.ltgame.ltgamesdkcore.util.LTGameUtil;
import com.sdk.ltgame.ltphone.uikit.PhoneActionActivity;


public class PhonePlatform extends AbsPlatform {


    private PhonePlatform(Context context, String appId, String appKey, int target) {
        super(context, appId, appKey,  target);
    }


    @Override
    public void login(Activity activity, int target, LoginObject object, OnLoginStateListener listener) {
        PhoneHelper mHelper = new PhoneHelper(activity, object.getmPhone(), object.getmPassword(),
                object.getmAdID(), object.getmLoginCode(), listener);
        mHelper.action();

    }

    @Override
    public void recharge(Activity activity, int target, RechargeObject object, OnRechargeListener listener) {

    }

    /**
     * 工厂类
     */
    public static class Factory implements PlatformFactory {

        @Override
        public IPlatform create(Context context, int target) {
            IPlatform platform = null;
            LTGameOptions options = LTGameSdk.options();
            if (!LTGameUtil.isAnyEmpty(options.getLtAppKey(), options.getLtAppId())) {
                platform = new PhonePlatform(context, options.getLtAppId(), options.getLtAppKey(), target);
            }
            return platform;
        }

        @Override
        public int getPlatformTarget() {
            return Target.PLATFORM_PHONE;
        }

        @Override
        public boolean checkLoginPlatformTarget(int target) {
            return target == Target.LOGIN_PHONE;
        }

        @Override
        public boolean checkRechargePlatformTarget(int target) {
            return false;
        }
    }

    @Override
    public Class getUIKitClazz() {
        return PhoneActionActivity.class;
    }
}
