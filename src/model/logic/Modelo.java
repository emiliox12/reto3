package model.logic;

import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ITablaSimbolosOrdenada;
import model.data_structures.RBT;
import model.data_structures.TablaHashSeparateChaining;
import model.utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ITablaSimbolos<String, Sentiment> sentimentsTable;
	private ITablaSimbolos<String, UserTrack> tracksTable;
	private ITablaSimbolos<String, ContextContent> contextTable;
	private ITablaSimbolosOrdenada<String, UserTrack> traksTree;
	private ITablaSimbolosOrdenada<String, ContextContent> contextTree;
	private ITablaSimbolosOrdenada<Float, ContextContent> contextTreeEnergy;
	private ITablaSimbolosOrdenada<Float, ContextContent> contextTreeDanceability;
	private ITablaSimbolosOrdenada<Float, ContextContent> contextTreeTempo;
	private ITablaSimbolosOrdenada<Float, ContextContent> contextTreeInstrumentales;
	private ITablaSimbolosOrdenada<Float, ContextContent> contextTreeTimes;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo() {
		sentimentsTable = new TablaHashSeparateChaining<>(400);
		tracksTable = new TablaHashSeparateChaining<>(400);
		contextTable = new TablaHashSeparateChaining<>(400);
		traksTree = new RBT<>();
		contextTree = new RBT<>();
		contextTreeEnergy = new RBT<>();
		contextTreeDanceability = new RBT<>();
		contextTreeTempo = new RBT<>();
		contextTreeInstrumentales = new RBT<>();
		contextTreeTimes = new RBT<>();
		// datos = new ListaEncadenada<YoutubeVideo>();
		cargar();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano() {
		return contextTable.size();
	}

	public void cargar() {
		System.out.println("Start upload");
		int count = 0;
		Reader in;
		long start = System.currentTimeMillis();
		try {
			in = new FileReader("./data/user_track.csv");
			Iterable<CSVRecord> tracks = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : tracks) {
				String user_id = record.get("user_id");
				String track_id = record.get("track_id");
				String hashtag = record.get("hashtag");
				String created_at = record.get("created_at");
				UserTrack track = new UserTrack(user_id, track_id, hashtag, created_at);
				tracksTable.put(track_id, track);
				traksTree.put(track_id, track);
			}
			in = new FileReader("./data/sentiment_values.csv");
			Iterable<CSVRecord> sentiments = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : sentiments) {
				String hashtag = record.get("hashtag");
				String vader_min = record.get("vader_min");
				String vader_max = record.get("vader_max");
				String vader_sum = record.get("vader_sum");
				String vader_avg = record.get("vader_avg");
				String afinn_min = record.get("afinn_min");
				String afinn_max = record.get("afinn_max");
				String afinn_sum = record.get("afinn_sum");
				String afinn_avg = record.get("afinn_avg");
				String ol_min = record.get("ol_min");
				String ol_max = record.get("ol_max");
				String ol_sum = record.get("ol_sum");
				String ol_avg = record.get("ol_avg");
				String ss_min = record.get("ss_min");
				String ss_max = record.get("ss_max");
				String ss_sum = record.get("ss_sum");
				String ss_avg = record.get("ss_avg");
				Sentiment sentiment = new Sentiment(hashtag, vader_min, vader_max, vader_sum, vader_avg, afinn_min,
						afinn_max, afinn_sum, afinn_avg, ol_min, ol_max, ol_sum, ol_avg, ss_min, ss_max, ss_sum,
						ss_avg);
				sentimentsTable.put(hashtag, sentiment);
			}
			in = new FileReader("./data/context_content.csv");
			Iterable<CSVRecord> contexts = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : contexts) {
				String instrumentalness = record.get("instrumentalness");
				String liveness = record.get("liveness");
				String speechiness = record.get("speechiness");
				String danceability = record.get("danceability");
				String valence = record.get("valence");
				String loudness = record.get("loudness");
				String tempo = record.get("tempo");
				String acousticness = record.get("acousticness");
				String energy = record.get("energy");
				String mode = record.get("mode");
				String key = record.get("key");
				String artist_id = record.get("artist_id");
				String tweet_lang = record.get("tweet_lang");
				String track_id = record.get("track_id");
				String created_at = record.get("created_at");
				String lang = record.get("lang");
				String time_zone = record.get("time_zone");
				String user_id = record.get("user_id");
				String id = record.get("id");
				String hour = (created_at.split("\\s")[1].trim());
				ContextContent context = new ContextContent(instrumentalness, liveness, speechiness, danceability,
						valence, loudness, tempo, acousticness, energy, mode, key, artist_id, tweet_lang, track_id,
						created_at, lang, time_zone, user_id, id);
				contextTable.put(id, context);
				contextTree.put(id, context);
				contextTreeEnergy.put(Float.parseFloat(energy), context);
				contextTreeDanceability.put(Float.parseFloat(danceability), context);
				contextTreeTempo.put(Float.parseFloat(tempo), context);
				contextTreeInstrumentales.put(Float.parseFloat(instrumentalness), context);
				contextTreeTimes.put(Float.parseFloat(hour), context);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Creaci??n: " + (end - start) + " #: " + count);
	}

	public String req1(String category_name, String country, int n) {
		String res = "";
		return res;
	}

	public String req2(float minEnergy, float maxEnergy, float minDanceability, float maxDanceability, int n) {
		String res = "";
		ILista<Float> energyKeys = contextTreeEnergy.keysInRange(minEnergy, maxEnergy);
		ILista<ContextContent> contexts = new ArregloDinamico<>(n);
		for (int i = 0; i < energyKeys.size(); i++) {
			ContextContent curr = contextTable.get("" + energyKeys.getElement(i));
			if (curr.danceability > minDanceability && curr.danceability < maxDanceability) {
				contexts.addLast(curr);
			}
			if (contexts.size() >= n) {
				break;
			}
		}
		for (int i = 0; i < contexts.size(); i++) {
			ContextContent curr = contexts.getElement(i);
			res += "Track " + i + " " + curr.track_id + " with energy of " + curr.energy + " and danceability of "
					+ curr.danceability + "\n";
		}
		return res;
	}

	public String req3(float minInstrumentalness, float maxInstrumentalness, float minTempo, float maxTempo,
			int n) {
		String res = "";
		ILista<Float> energyKeys = contextTreeInstrumentales.keysInRange(minInstrumentalness, maxInstrumentalness);
		ILista<ContextContent> contexts = new ArregloDinamico<>(n);
		for (int i = 0; i < energyKeys.size(); i++) {
			ContextContent curr = contextTable.get("" + energyKeys.getElement(i));
			if (curr.tempo > minTempo && curr.tempo < maxTempo) {
				contexts.addLast(curr);
			}
			if (contexts.size() >= n) {
				break;
			}
		}
		for (int i = 0; i < contexts.size(); i++) {
			ContextContent curr = contexts.getElement(i);
			res += "Track " + i + " " + curr.track_id + " with instrumentalness of " + curr.instrumentalness
					+ " and tempo of " + curr.tempo + "\n";
		}
		return res;
	}

	public String req4() {
		String res = "";
		ILista<Float> reggae = contextTreeTempo.keysInRange(60f, 90f);
		ILista<Float> downTempo = contextTreeTempo.keysInRange(70f, 100f);
		ILista<Float> chillOut = contextTreeTempo.keysInRange(90f, 120f);
		ILista<Float> hipHop = contextTreeTempo.keysInRange(85f, 115f);
		ILista<Float> jazzFunk = contextTreeTempo.keysInRange(120f, 125f);
		ILista<Float> pop = contextTreeTempo.keysInRange(100f, 130f);
		ILista<Float> rNB = contextTreeTempo.keysInRange(60f, 80f);
		ILista<Float> rock = contextTreeTempo.keysInRange(110f, 140f);
		ILista<Float> metal = contextTreeTempo.keysInRange(100f, 160f);
		res += "reggae: " + reggae.size() + "\n";
		res += "downTempo: " + downTempo.size() + "\n";
		res += "chillOut: " + chillOut.size() + "\n";
		res += "hipHop: " + hipHop.size() + "\n";
		res += "jazzFunk: " + jazzFunk.size() + "\n";
		res += "pop: " + pop.size() + "\n";
		res += "rNB: " + rNB.size() + "\n";
		res += "rock: " + rock.size() + "\n";
		res += "metal: " + metal.size() + "\n";
		return res;
	}

	public String req5(float minHour, float maxHour) {
		String res = "";
		ILista<ContextContent> contexts = contextTreeTimes.valuesInRange(minHour, maxHour);
		for (int i = 0; i < 10; i++) {
			ContextContent curr = contexts.getElement(i);
			UserTrack track = tracksTable.get(curr.track_id);
			Sentiment sentiment = sentimentsTable.get(track.hashtag);			
			res += "Track " + i + ": " + curr.track_id + sentiment.vader_avg;
		}
		return res;
	}

	@Override
	public String toString() {
		return "";
	}
}
