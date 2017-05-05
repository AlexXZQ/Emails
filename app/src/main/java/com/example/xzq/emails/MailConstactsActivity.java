package com.example.xzq.emails;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xzq.emails.app.MyApplication;
import com.example.xzq.emails.bean.EmailUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xzq on 2017/4/17.
 */

public class MailConstactsActivity extends Activity {

    private ListView lv;
    private List<EmailUsers> list;
    private ProgressDialog dialog;
    private Uri uri = Uri.parse("content://com.emailconstantprovider");
    private Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_constact);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正加载");
        dialog.show();

        list = getAllConstacts();
        init();

        dialog.dismiss();
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.constacts_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     * 长按事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) info.id;
        switch (item.getItemId()) {
            case R.id.update:
                updateAddress(list.get(id).getName(), list.get(id).getAddress());
                break;
            case R.id.delete:
                deleteAddress(list.get(id).getName(), list.get(id).getAddress());
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 更新数据
     *
     * @param name
     * @param address
     */
    private void updateAddress(final String name, String address) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MailConstactsActivity.this);
        builder.setTitle("修改邮箱地址");
        final EditText edit = new EditText(MailConstactsActivity.this);
        edit.setText(address);
        builder.setView(edit);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContentValues values = new ContentValues();
                values.put("address", edit.getText().toString().trim());
                getContentResolver().update(uri, values, "mailfrom=? and name=?", new String[]{MyApplication.info.getUserName(), name});

                list = getAllConstacts();
                adapter.notifyDataSetChanged();
                Toast.makeText(MailConstactsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /**
     * 删除数据
     *
     * @param name
     * @param address
     */
    private void deleteAddress(final String name, String address) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MailConstactsActivity.this);
        builder.setMessage("你确定要删除数据");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getContentResolver().delete(uri, "mailfrom=? and name=?", new String[]{MyApplication.info.getUserName(), name});

                list = getAllConstacts();
                adapter.notifyDataSetChanged();
                Toast.makeText(MailConstactsActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /**
     * 查询所有联系人
     *
     * @return
     */
    private List<EmailUsers> getAllConstacts() {
        List<EmailUsers> users = new ArrayList<EmailUsers>();
        Cursor c = getContentResolver().query(uri, null, "mailfrom=?", new String[]{MyApplication.info.getUserName()}, null);
        while (c.moveToNext()) {
            EmailUsers user = new EmailUsers(c.getInt(0), c.getString(2), c.getString(3));
            users.add(user);
        }
        return users;
    }

    /**
     * 初始化
     */
    private void init() {
        lv = (ListView) findViewById(R.id.lv_constant);

        adapter = new Myadapter();
        lv.setAdapter(adapter);

    }

    /**
     * 返回
     *
     * @param v
     */
    public void back(View v) {
        finish();
    }

    /**
     * 适配器
     */
    private class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = View.inflate(MailConstactsActivity.this, R.layout.email_constact_item, null);
            TextView name = (TextView) item.findViewById(R.id.tv_name);
            TextView address = (TextView) item.findViewById(R.id.tv_address);

            EmailUsers user = list.get(position);
            name.setText(user.getName());
            address.setText(user.getAddress());
            return item;
        }

    }

}
