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

import com.pradeep.karak.Activity.BaseActivity;
import com.pradeep.karak.R;
import com.pradeep.karak.databinding.FragmentChildAdminBinding;


public class FragmentChildAdmin extends Fragment {
    private FragmentChildAdminBinding mBinding;
    private String[] mainMenuList;
    BaseActivity mActivity;
    String data = "";

    public FragmentChildAdmin(String data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_child_admin, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainMenuList = new String[]{"Statistics", "Boil Time", "Set Dispense Ratio", "Set Password",
                "Serial Number","Machine Cleaning"};
        mBinding.autoComplete.setDropDownBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_brown_bar));
        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildStatistics(data), "INIT_STATISTICS").commit();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_autocomplete, mainMenuList);
        mBinding.autoComplete.setAdapter(arrayAdapter);

        mBinding.autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildStatistics("emptyData"), "TAG_STATISTICS").commit();
                        break;
                    case 1:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildBoilTime(), "TAG_BOIL_TIME").commit();
                        break;
                    case 2:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildDispenseRatio(), "TAG_DISPENSE").commit();
                        break;
                    case 3:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildSetPassword(), "TAG_SET_PASSWORD").commit();
                        break;
                    case 4:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildMachineNumber(), "TAG_MACHINE_NUMBER").commit();
                        break;
                    case 5:
                        getParentFragmentManager().beginTransaction().replace(mBinding.adminFragHost.getId(), new FragmentAdSubChildMachineCleaning(), "TAG_MACHINE_CLEANING").commit();
                        break;
                }
            }
        });
    }
}
