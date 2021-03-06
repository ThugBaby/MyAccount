package com.yzy.myaccount.activity;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_typeDao;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class TypeActivity extends Activity implements OnClickListener {
    
    private ListView listview;
    private Context  context;
    private List<String> array = new ArrayList<String>();
    private List<String> selectid = new ArrayList<String>();
    private boolean isMulChoice = false; //是否多选
    private Adapter  adapter;
    private RelativeLayout layout;
    private Button cancle,delete;
    private ImageView imageView,add;
    private TextView txtcount;
  private Tb_typeDao dao;
  public static TypeActivity instance=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typemanage);
        instance=this;
          dao=new Tb_typeDao(this);
        context = this;
        listview = (ListView)findViewById(R.id.list);
        layout = (RelativeLayout)findViewById(R.id.relative);
        txtcount = (TextView)findViewById(R.id.txtcount);
        cancle   = (Button)findViewById(R.id.cancle);
        delete   = (Button)findViewById(R.id.delete);
        add=(ImageView)findViewById(R.id.add);
        cancle.setOnClickListener(this);
        delete.setOnClickListener(this);
        add.setOnClickListener(this);
        imageView=(ImageView)findViewById(R.id.topback3);
		
	       imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        init();
        adapter = new Adapter(context,txtcount);
        listview.setAdapter(adapter);
        
    }
    
    void init()
    {
    	
        array=dao.getAll();
    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.cancle:
            isMulChoice = false;
            selectid.clear();
            adapter = new Adapter(context,txtcount);
            listview.setAdapter(adapter);
            layout.setVisibility(View.INVISIBLE);
            break;
        case R.id.delete:
            isMulChoice =false;
            for(int i=0;i<selectid.size();i++){
        		dao.delete(selectid.get(i));
            }
            	for(int i=0;i<selectid.size();i++){		
                for(int j=0;j<array.size();j++){
                    if(selectid.get(i).equals(array.get(j))){
                    	
                        array.remove(j);
                        
                    }
                }
            }
            selectid.clear();
            adapter = new Adapter(context,txtcount);
            listview.setAdapter(adapter);
            layout.setVisibility(View.INVISIBLE);
            break;
        case R.id.add:
        	Intent intent=new Intent(TypeActivity.this,AddTypeActivity.class);
			//intent.putExtra("tag",1);				
			startActivity(intent);							
			
        	break;
        default:
            break;
        }
        
    }
    
    class Adapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater=null;
        private HashMap<Integer, View> mView ;
        public  HashMap<Integer, Integer> visiblecheck ;//用来记录是否显示checkBox
        public  HashMap<Integer, Boolean> ischeck;
        private TextView txtcount;
        public Adapter(Context context,TextView txtcount)
        {
            this.context = context;
            this.txtcount = txtcount;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = new HashMap<Integer, View>();
            visiblecheck = new HashMap<Integer, Integer>();
            ischeck      = new HashMap<Integer, Boolean>();
            if(isMulChoice){
                for(int i=0;i<array.size();i++){
                    ischeck.put(i, false);
                    visiblecheck.put(i, CheckBox.VISIBLE);
                }
            }else{
                for(int i=0;i<array.size();i++)
                {
                    ischeck.put(i, false);
                    visiblecheck.put(i, CheckBox.INVISIBLE);
                }
            }
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return array.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return array.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = mView.get(position);
            if(view==null)
            {
                view = inflater.inflate(R.layout.typeitem, null);
                TextView txt = (TextView)view.findViewById(R.id.txtName);
                final CheckBox ceb = (CheckBox)view.findViewById(R.id.check);
                
                txt.setText(array.get(position));//设置数据
                
                ceb.setChecked(ischeck.get(position));
                ceb.setVisibility(visiblecheck.get(position));
                
                view.setOnLongClickListener(new Onlongclick());
                
                view.setOnClickListener(new OnClickListener() {
                    
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if(isMulChoice){
                            if(ceb.isChecked()){
                                ceb.setChecked(false);
                                selectid.remove(array.get(position));
                            }else{
                                ceb.setChecked(true);
                                selectid.add(array.get(position));
                            }
                            txtcount.setText("共选择了"+selectid.size()+"项");
                        }else {
                            Toast.makeText(context, "点击了"+array.get(position), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                
                mView.put(position, view);
            }
            return view;
        }
        
        class Onlongclick implements OnLongClickListener{

            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                
                isMulChoice = true;
                selectid.clear();
                layout.setVisibility(View.VISIBLE);
                for(int i=0;i<array.size();i++)
                {
                    adapter.visiblecheck.put(i, CheckBox.VISIBLE);
                }
                adapter = new Adapter(context,txtcount);
                listview.setAdapter(adapter);
                return true;
            }
        }
    }
}
