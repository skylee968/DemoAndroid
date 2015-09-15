package com.onetech.mobilereader.entity;

import com.google.gson.annotations.SerializedName;

public class BookEntity extends BaseEntity {
	@SerializedName("_id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("image")
	private String imageCover;

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("description")
	private String description;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("date_updated")
	private String dateUpdated;

	@SerializedName("likes")
	private long totalLike;

	@SerializedName("epub")
	private String bookLink;

	@SerializedName("meta")
	private MetaBookEntity meta;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("author")
	private AuthorEntity author;

	@SerializedName("in_suggest")
	private boolean suggestBook;

	@SerializedName("download")
	private int totalDownload;

	@SerializedName("status")
	private String status;

	@SerializedName("price")
	private int price;

	@SerializedName("is_new")
	private boolean isNew;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("is_free")
	private boolean isFrees;

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
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public long getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(long totalLike) {
		this.totalLike = totalLike;
	}
	public String getImageCover() {
		return imageCover;
	}
	public void setImageCover(String imageCover) {
		this.imageCover = imageCover;
	}

	public String getBookLink() {
		return bookLink;
	}

	public void setBookLink(String bookLink) {
		this.bookLink = bookLink;
	}

	public MetaBookEntity getMeta() {
		return meta;
	}

	public void setMeta(MetaBookEntity meta) {
		this.meta = meta;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public boolean isSuggestBook() {
		return suggestBook;
	}

	public void setSuggestBook(boolean suggestBook) {
		this.suggestBook = suggestBook;
	}

	public int getTotalDownload() {
		return totalDownload;
	}

	public void setTotalDownload(int totalDownload) {
		this.totalDownload = totalDownload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isFrees() {
		return isFrees;
	}

	public void setIsFrees(boolean isFrees) {
		this.isFrees = isFrees;
	}
}
