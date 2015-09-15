package com.onetech.mobilereader.entity;

import com.google.gson.annotations.SerializedName;

public class CategoryEntity extends BaseEntity {
	@SerializedName("_id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("description")
	private String description;

	@SerializedName("date_created")
	private String dateCreated;

	private String imageCover;

	@SerializedName("book_count")
	private int counter;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getImageCover() {
		return imageCover;
	}

	public void setImageCover(String imageCover) {
		this.imageCover = imageCover;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
