package model.logic;

public class Sentiment implements Comparable<Sentiment>{

	public String hashtag;
	public String vader_min;
	public String vader_max;
	public String vader_sum;
	public String vader_avg;
	public String afinn_min;
	public String afinn_max;
	public String afinn_sum;
	public String afinn_avg;
	public String ol_min;
	public String ol_max;
	public String ol_sum;
	public String ol_avg;
	public String ss_min;
	public String ss_max;
	public String ss_sum;
	public String ss_avg;

	public Sentiment(String hashtag, String vader_min, String vader_max, String vader_sum, String vader_avg,
			String afinn_min, String afinn_max, String afinn_sum, String afinn_avg, String ol_min, String ol_max,
			String ol_sum, String ol_avg, String ss_min, String ss_max, String ss_sum, String ss_avg) {
		this.hashtag = hashtag;
		this.vader_min = vader_min;
		this.vader_max = vader_max;
		this.vader_sum = vader_sum;
		this.vader_avg = vader_avg;
		this.afinn_min = afinn_min;
		this.afinn_max = afinn_max;
		this.afinn_sum = afinn_sum;
		this.afinn_avg = afinn_avg;
		this.ol_min = ol_min;
		this.ol_max = ol_max;
		this.ol_sum = ol_sum;
		this.ol_avg = ol_avg;
		this.ss_min = ss_min;
		this.ss_max = ss_max;
		this.ss_sum = ss_sum;
		this.ss_avg = ss_avg;
	}

	@Override
	public int compareTo(Sentiment other) {
		return hashtag.compareTo(other.hashtag);
	}

}
