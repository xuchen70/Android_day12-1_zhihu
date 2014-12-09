package com.android.day12_1_zhihu.entity;

public class Stories {
	private String title,share_url,ga_prefix;
	private String[] images;
	private boolean multipic;
	private int type;
	private long id;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShare_url() {
		return share_url;
	}
	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	public String getGa_prefix() {
		return ga_prefix;
	}
	public void setGa_prefix(String ga_prefix) {
		this.ga_prefix = ga_prefix;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public boolean isMultipic() {
		return multipic;
	}
	public void setMultipic(boolean multipic) {
		this.multipic = multipic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
