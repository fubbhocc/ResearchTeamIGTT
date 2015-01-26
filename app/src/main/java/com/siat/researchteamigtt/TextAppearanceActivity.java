/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.siat.researchteamigtt;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

/**
 * Creates a card scroll view with examples of the default textAppearance styles on Glass.
 */
public final class TextAppearanceActivity extends Activity {

    private enum Action {
        INSTRUCTIONS(R.string.INSTRUCTIONS_D,R.drawable.glasslogod),
        XING_LEI(R.string.XING_LEI_D, R.drawable.xingleid),
        XIE_YAO_QIN(R.string.XIE_YAO_QIN_D, R.drawable.xieyaoqind),
        WANG_LEI(R.string.WANG_LEI_D, R.drawable.wangleid),
        GU_JIA(R.string.GU_JIA_D, R.drawable.gujiad),
        CHEN_YAN(R.string.CHEN_YAN_D, R.drawable.chenyand),
        ZHOU_SHOU_JUN(R.string.ZHOU_SHOU_JUN_D, R.drawable.zhoushoujund),
        ZHOU_YUNG_JIN(R.string.ZHOU_YUNG_JIN_D, R.drawable.zhouyongjind),
        LI_ZHI_CHENG(R.string.LI_ZHI_CHENG_D, R.drawable.lizhichengd),
        XIONG_JIN(R.string.XIONG_JING_D, R.drawable.xiongjind),
        WEN_NING(R.string.WEN_NING_D, R.drawable.wenningd),
        RONG_ZI(R.string.RONG_YI_D, R.drawable.rongzid),
        WANG_LE_JING(R.string.WANG_LE_JING_D, R.drawable.wanglejingd);

        final int textId;
        final int imageId;

        Action(int textId, int imageId) {
            this.textId = textId;
            this.imageId = imageId;
        }
    }
    
    private CardScrollView mCardScroller;
    private CardAdapterWithMutations mAdapter;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        int value = intent.getFlags();
        mCardScroller = new CardScrollView(this);
        setupAdapter(value);
        //setupClickListener();
        setContentView(mCardScroller);
        
    }

    private void setupAdapter(int value) {
        mAdapter = new CardAdapterWithMutations();
        // Insert initial cards, one of each kind.
        //int position = 0;
        Action action = Action.values()[value];
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT_FIXED);
        card.setText(action.textId).addImage(action.imageId).setFootnote(R.string.CANCEL);
        mAdapter.insertCardWithoutNotification(0, card, action);
        // Setting adapter notifies the card scroller of new content.
        mCardScroller.setAdapter(mAdapter);
    }

    private final class CardAdapterWithMutations extends CardAdapter {

        private final List<Action> mActions;

        public CardAdapterWithMutations() {
            super(new ArrayList<CardBuilder>());
            mActions = new ArrayList<Action>();
        }

        /**
         * Inserts a card into the adapter, without notifying.
         */

        public void insertCardWithoutNotification(int position, CardBuilder card, Action action) {
            mCards.add(position, card);
            mActions.add(position, action);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }
}
