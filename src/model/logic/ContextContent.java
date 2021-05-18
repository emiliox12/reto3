package model.logic;

public class ContextContent implements Comparable<ContextContent>{
	public String instrumentalness;
	public String liveness;
	public String speechiness;
	public String danceability;
	public String valence;
	public String loudness;
	public String tempo;
	public String acousticness;
	public String energy;
	public String mode;
	public String key;
	public String artist_id;
	public String tweet_lang;
	public String track_id;
	public String created_at;
	public String lang;
	public String time_zone;
	public String user_id;
	public String id;


	public ContextContent(String instrumentalness,
			String liveness,
			String speechiness,
			String danceability,
			String valence,
			String loudness,
			String tempo,
			String acousticness,
			String energy,
			String mode,
			String key,
			String artist_id,
			String tweet_lang,
			String track_id,
			String created_at,
			String lang,
			String time_zone,
			String user_id,
			String id) {
		this.instrumentalness = instrumentalness;
		this.liveness = liveness;
		this.speechiness = speechiness;
		this.danceability = danceability;
		this.valence = valence;
		this.loudness = loudness;
		this.tempo = tempo;
		this.acousticness = acousticness;
		this.energy = energy;
		this.mode = mode;
		this.key = key;
		this.artist_id = artist_id;
		this.tweet_lang = tweet_lang;
		this.track_id = track_id;
		this.created_at = created_at;
		this.lang = lang;
		this.time_zone = time_zone;
		this.user_id = user_id;
		this.id = id;
	}


	@Override
	public int compareTo(ContextContent arg0) {
		return id.compareTo(arg0.id);
	}

}
