package model.logic;

public class UserTrack implements Comparable<UserTrack>{

	public String user_id;
	public String track_id;
	public String hashtag;
	public String created_at;

	public UserTrack(String user_id, String track_id, String hashtag, String created_at) {
		this.user_id = user_id;
		this.track_id = track_id;
		this.hashtag = hashtag;
		this.created_at = created_at;
	}

	@Override
	public int compareTo(UserTrack arg0) {
		return track_id.compareTo(arg0.track_id);
	}

}
