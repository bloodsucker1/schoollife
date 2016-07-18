package com.example.work.adapter;

import java.util.ArrayList;

import com.example.work.holder.BaseHolder;
import com.example.work.holder.MoreHolder;
import com.example.work.manager.ThreadPoolManager;
import com.example.work.utils.UIUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * @author Administrator
 *
 */
public abstract class MyAdapter<T> extends BaseAdapter{

	private ArrayList<T> arrayList;//填充数据
	
	private static final int TYPE_NORMAL = 1;//正常情况
	private static final int TYPE_MORE = 0;//更多
	
	public MyAdapter(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		return arrayList.size()+1;
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		if(position ==arrayList.size()){
			return TYPE_MORE;
		}else{
			return othertype(position);
		}
	}
	//提供接口给子类写其他类型
	public int othertype(int position){
		return TYPE_NORMAL;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder;
		if(convertView==null){
			if(getItemViewType(position)!=TYPE_MORE){
				holder = getHolder(position);
			}else{
				holder = new MoreHolder(hasMore());
			}
		}else{
			holder = (BaseHolder) convertView.getTag();
		}
		
		//4.刷新控件数据
		if(getItemViewType(position)!=TYPE_MORE){
			holder.setData(arrayList.get(position));//不是加载更多才加载数据
		}else{
			// 加载下一页数据
				MoreHolder moreHolder = (MoreHolder) holder;//同一个holder
				
//				if (moreHolder.getData() == MoreHolder.TYPE_HAS_MORE) {// 有更多数据,才加载更多数据
				//不用进行判断，判断会出现关闭重新加载错误，可以写成两部分先判断是否还有数据再进行加载，就能把注释去掉	
				LoadMore(moreHolder);
//				}
		}
		return holder.getMyRootView();
	}
	/**
	 * @return
	 * 是否可以加载更多，子类可以重写
	 */
	public boolean hasMore() {
		return true;
	}

	public abstract BaseHolder<T> getHolder(int position);
	
	/**
	 * 加载更多
	 */
	
	private boolean isLoadMore = false;// 标记当前是否正在加载更多
	
	public void LoadMore(final MoreHolder holder){
		if(!isLoadMore){// 判断当前是否没有加载更多, 避免重复加载更多数据
			isLoadMore = true;
			ThreadPoolManager manager = new ThreadPoolManager();
			manager.getThreadPool().execute(new Runnable() {
				
				@Override
				public void run() {

					//加载上一页数据
					final ArrayList<T> arrayList = (ArrayList<T>) loadData();
					UIUtils.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(arrayList!=null){
								if(arrayList.size()<20){
									// 每页数据有20条
									// 如果返回的数据条数少于20条,说明没有更多数据了
									holder.setData(MoreHolder.TYPE_NO_MORE);
								}else{
									holder.setData(MoreHolder.TYPE_HAS_MORE);
								}
								//将数据追加到集合末尾
								MyAdapter.this.arrayList.addAll(arrayList);
								//刷新界面
								notifyDataSetChanged();
							}else{
								// 加载更多失败,刷新加载更多的布局
								holder.setData(MoreHolder.TYPE_MORE_ERROR);
							}
						isLoadMore = false;
						}
				});
				}
			});
		}
	}
	
	public abstract ArrayList<T> loadData();
	/*获取集合大小
	 * @return
	 */
	public int getListSize(){
		return arrayList.size();
	}
}
