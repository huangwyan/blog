package com.example.administrator.expert_day01_chabaike.beans;


import android.os.Parcel;
import android.os.Parcelable;

public class  Info implements Parcelable {
	private String title;//资讯标题
	private int infoclass;//分类
	private String img;//图片
	private String description;//描述
	private String keywords;//关键字
	private int count ;//访问次数
	private int fcount;//收藏数
	private int rcount;//评论读数
	private String time;


	private long id;

	public Info(){
	}

	protected Info(Parcel in) {
		img = in.readString();
		description = in.readString();
		rcount = in.readInt();
		time = in.readString();
		id = in.readLong();
	}


	public static final Creator<Info> CREATOR = new Creator<Info>() {
		@Override
		public Info createFromParcel(Parcel in) {
			return new Info(in);
		}

		@Override
		public Info[] newArray(int size) {
			return new Info[size];
		}
	};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getInfoclass() {
		return infoclass;
	}

	public void setInfoclass(int infoclass) {
		this.infoclass = infoclass;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFcount() {
		return fcount;
	}

	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(img);
		dest.writeString(description);
		dest.writeInt(rcount);
		dest.writeString(time);
		dest.writeLong(id);
	}
}