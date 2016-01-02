/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 *
 * MediaTek Inc. (C) 2010. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */

package com.mediatek.phone.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.mediatek.common.PluginImpl;
import com.mediatek.phone.ext.DefaultCallFeaturesSettingExt;
import com.mediatek.op01.plugin.R;

@PluginImpl(interfaceName="com.mediatek.phone.ext.ICallFeaturesSettingExt")
public class Op01CallFeaturesSettingExt extends DefaultCallFeaturesSettingExt {
    private static final String LOG_TAG = "Op01CallFeaturesSettingExt";
    private static final String BUTTON_CALL_REJECTION_KEY = "button_call_rejection_key";
    private static final String BUTTON_TTY_KEY = "button_tty_mode_key";
    private static final String BUTTON_SIP_KEY = "sip_settings_preference_screen_key";

    @Override
    public void initOtherCallFeaturesSetting(PreferenceFragment fragment) {
        Context cont = null;
        log("initOtherCallFeaturesSettings PreferenceFragment");

        ListPreference buttonTTYPreference =
                (ListPreference) fragment.getPreferenceScreen().findPreference(BUTTON_TTY_KEY);
        if (buttonTTYPreference != null) {
            fragment.getPreferenceScreen().removePreference(buttonTTYPreference);
        }

        PreferenceScreen buttonSipPreference =
                (PreferenceScreen) fragment.getPreferenceScreen().findPreference(BUTTON_SIP_KEY);
        if (buttonSipPreference != null) {
            fragment.getPreferenceScreen().removePreference(buttonSipPreference);
        }
    }

    @Override
    public void initOtherCallFeaturesSetting(PreferenceActivity activity) {
        TelephonyManager telephonyManager =
            (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.isMultiSimEnabled()) {
            log("is multi sim, so return");
            return;
        }

        ListPreference buttonTTYPreference =
                (ListPreference) activity.getPreferenceScreen().findPreference(BUTTON_TTY_KEY);
        if (buttonTTYPreference != null) {
            activity.getPreferenceScreen().removePreference(buttonTTYPreference);
        }

        PreferenceScreen buttonSipPreference =
                (PreferenceScreen) activity.getPreferenceScreen().findPreference(BUTTON_SIP_KEY);
        if (buttonSipPreference != null) {
            activity.getPreferenceScreen().removePreference(buttonSipPreference);
        }
    }

    public void log(String msg) {
        Log.d(LOG_TAG, msg);
    }
}
