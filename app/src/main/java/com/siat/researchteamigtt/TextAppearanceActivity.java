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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

/**
 * Creates a card scroll view with examples of the default textAppearance styles on Glass.
 */
public final class TextAppearanceActivity extends Activity {

    private enum Action {
        INSTRUCTIONS(R.string.INSTRUCTIONS,R.drawable.glasslogo),
        XING_LEI(R.string.XING_LEI, R.drawable.xinglei),
        XIE_YAO_QIN(R.string.XIE_YAO_QIN, R.drawable.xieyaoqin),
        WANG_LEI(R.string.WANG_LEI, R.drawable.wanglei),
        GU_JIA(R.string.GU_JIA, R.drawable.gujia),
        CHEN_YAN(R.string.CHEN_YAN, R.drawable.chenyan),
        ZHOU_SHOU_JUN(R.string.ZHOU_SHOU_JUN, R.drawable.zhoushoujun),
        ZHOU_YUNG_JIN(R.string.ZHOU_YUNG_JIN, R.drawable.zhouyongjin),
        LI_ZHI_CHENG(R.string.LI_ZHI_CHENG, R.drawable.lizhicheng),
        XIONG_JIN(R.string.XIONG_JING, R.drawable.xiongjin),
        WEN_NING(R.string.WEN_NING, R.drawable.wenning),
        RONG_ZI(R.string.RONG_YI, R.drawable.rongzi),
        WANG_LE_JING(R.string.WANG_LE_JING, R.drawable.wanglejing);

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
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText(action.textId).addImage(action.imageId);
        mAdapter.insertCardWithoutNotification(value, card, action);
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
