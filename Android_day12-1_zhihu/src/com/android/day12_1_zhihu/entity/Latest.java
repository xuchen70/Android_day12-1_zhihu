package com.android.day12_1_zhihu.entity;

import java.util.List;

public class Latest {
	private String date;
	private List<Stories> story;
	private List<TopStories>  topStory;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Stories> getStory() {
		return story;
	}
	public void setStory(List<Stories> story) {
		this.story = story;
	}
	public List<TopStories> getTopStory() {
		return topStory;
	}
	public void setTopStory(List<TopStories> topStory) {
		this.topStory = topStory;
	}
	
}
