package com.yzy.myaccount.fragment;

import java.io.IOException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzy.myaccount.R;
import com.yzy.myaccount.activity.MainActivity;
import com.yzy.myaccount.update.UpdateManager;

public class LeftFragment extends Fragment implements OnClickListener{
	private View mainView;
	private View setView;
	private View helpView;
	private View feedbackView;
	private View updateView;
	private View aboutView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_menu, null);
		findViews(view);
		
		return view;
	}
	
	
	public void findViews(View view) {
		mainView = view.findViewById(R.id.tvToday);
		setView = view.findViewById(R.id.tvLastlist);
		helpView = view.findViewById(R.id.tvDiscussMeeting);
		feedbackView = view.findViewById(R.id.tvMyFavorites);
		updateView = view.findViewById(R.id.tvUpdate);
		aboutView = view.findViewById(R.id.tvMySettings);
		
		mainView.setOnClickListener(this);
		setView.setOnClickListener(this);
		helpView.setOnClickListener(this);
		feedbackView.setOnClickListener(this);
		updateView.setOnClickListener(this);
		aboutView.setOnClickListener(this);
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		String title = null;
		switch (v.getId()) {
		case R.id.tvToday: // 首頁
			newContent = new menuFragment();
			title = getString(R.string.main);
			break;
		case R.id.tvLastlist:// 设置
			newContent = new SetFragment();
			title = getString(R.string.set);
			break;
		case R.id.tvDiscussMeeting: // 
			newContent = new MyHelpFragment();
			title = getString(R.string.help);
			break;
		case R.id.tvMyFavorites: // 
			newContent = new FBFragment();
			title = getString(R.string.feedback);
			break;
		case R.id.tvUpdate: // 更新
			Toast.makeText((MainActivity)getActivity(), "点击", 1000);
			UpdateManager manager = new UpdateManager((MainActivity)getActivity());
			// 检查软件更新
			try {
				manager.checkUpdate();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tvMySettings: // 
			newContent = new MyAboutFragment();
			title = getString(R.string.about);
			break;
		default:
			break;
		}
		if (newContent != null) {
			switchFragment(newContent, title);
		}
	}
	
	/**
	 * 切换fragment
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment, String title) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchConent(fragment, title);
		}
	}
	
}
