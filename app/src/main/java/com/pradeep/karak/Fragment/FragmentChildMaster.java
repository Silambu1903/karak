package com.pradeep.karak.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.pradeep.karak.R;
import com.pradeep.karak.databinding.FragmentChildMasterBinding;

// Created on 20 Mar 2021 by silambu
public class FragmentChildMaster extends Fragment {
    FragmentChildMasterBinding mBinding;
    private String[] masterMenuList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_child_master, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        masterMenuList = new String[]{"Flow rate", "Correction factor", "Preset Boil Time", "Factory Reset", "Total Reset"};
        mBinding.autoCompleteFlowrate.setDropDownBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_brown_bar));
        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildFlowRate(), "TAG_FlowRate").commit();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_autocomplete, masterMenuList);
        mBinding.autoCompleteFlowrate.setAdapter(arrayAdapter);
        mBinding.autoCompleteFlowrate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildFlowRate(), "TAG_FlowRate").commit();
                        break;
                    case 1:
                        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildCorrectionFactor(), "TAG_Correction").commit();
                        break;
                    case 2:
                        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildPresetBoilTime(), "TAG_BoilTime").commit();
                        break;

                    case 3:
                        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildFactoryReset(), "TAG_FactoryReset").commit();
                        break;

                    case 4:
                        getParentFragmentManager().beginTransaction().replace(mBinding.masterFragHost.getId(), new FragmentMaSubChildTotalReset(), "TAG_TotalReset").commit();
                        break;
                }
            }
        });
    }
}
