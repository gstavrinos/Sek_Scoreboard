package stavrinos.sek.helpers;

public class Row {

	  private String name;
	  private int score;
	  private String time;
	  private int obstacles;

	  
	  public Row() {
	  }
	  public Row(String name, int score, String time, int obstacles) {
	      this.name = name;
	      this.time = time;
	      this.score = score;
	      this.obstacles = obstacles;
	  }
	  
	  public String getName() {
	      return name;
	  }

	  public void setName(String name) {
	      this.name = name;
	  }

	  public String getTime() {
	      return time;
	  }

	  public void setTime(String time) {
	      this.time = time;
	  }

	  public int getScore() {
	      return score;
	  }

	  public void setScore(int score) {
	      this.score = score;
	  }

	  public int getObstacles() {
	      return obstacles;
	  }

	  public void setPostalCode(int obstacles) {
	      this.obstacles = obstacles;
	  }

	
}
