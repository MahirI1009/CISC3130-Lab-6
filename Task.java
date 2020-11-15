/* custom Task class, a one-size-fits-all for all the types of task required in this course such as Practice Problems, Labs, Midterms and the Final.
 * 
 * There's a field for the name of the task, the percent of the final grade that the task is worth, the amount of points that's possible to get on it,
 * the score that the user got on that particular assignment if the assignment was completed, and if it wasn't then a boolean variable labeled completed
 * will remain false. 
 * 
 * There's a constructor which takes the name of the task, the possible points and percentage of the grade as arguments.
 * 
 * There's a setter method which sets the myScore field to the user's score on particular assignment and sets completed to true.
 * 
 * There's two getter methodsm one which returns the possible points that can be gotten from completing the task and another that returns the user's score.*/
public class Task {
	protected String taskName;
	protected int percentOfGrade;
	protected int possiblePoints;
	protected int myScore;
	boolean completed = false;
	
	public Task(String taskName, int percentOfGrade, int possiblePoints) {
		this.taskName = taskName;
		this.percentOfGrade = percentOfGrade;
		this.possiblePoints = possiblePoints;
	}
	
	public void setMyScore(int score) {
		myScore = score;
		completed = true;
	}
	
	public int getPossiblePoints() {return possiblePoints;}
	
	public int getMyScore() {return myScore;}

} //end of class