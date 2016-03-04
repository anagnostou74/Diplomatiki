package gr.mobap.rss;

/**
 * A representation of an rss item from the list.
 * 
 * @author Veaceslav Grec
 * 
 */
public class Item {

	private final String title;
	private final String link;

	public Item(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}
}
