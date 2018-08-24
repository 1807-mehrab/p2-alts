package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Game {
	@Id
	private long game_id;
	private String title, price, photo;
	private int copies;

	public long getGame_id() {
		return game_id;
	}

	public void setGame_id(long game_id) {
		this.game_id = game_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	@Override
	public String toString() {
		return "Game [game_id=" + game_id + ", title=" + title + ", price=" + price + ", photo=" + photo + ", copies="
				+ copies + "]";
	}
}
