import java.util.Scanner;

public class Lab6 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); //this scanner is to take user input from the keyboard
		
		/* In this main method there are 3 arrays of Task objects and one instance of a Task object. The 3 task array are for Practice Problems,
		 * Labs and Midterms while the Task object thats not an array is for the final exam. Task is a custom object, its a simple class that takes
		 * 3 parameters, the name of the task, the percent of the overall grade its worth, and how many points you can possibly get on it.*/
		
		//array of task objects for the 8 practice problems assignments
		Task[] PracticeProblems = {
				new Task("Practice Problems #1", 2, 2),
				new Task("Practice Problems #2", 6, 6),
				new Task("Practice Problems #3", 6, 6),
				new Task("Practice Problems #4", 6, 6),
				new Task("Practice Problems #5", 6, 6),
				new Task("Practice Problems #6", 6, 6),
				new Task("Practice Problems #7", 6, 6),
				new Task("Practice Problems #8", 6, 6)
		};
		
		//array of task objects for the 7 lab assignments
		Task [] Labs = {
				new Task("Lab #1", 2, 1),
				new Task("Lab #2", 2, 2),
				new Task("Lab #3", 2, 2),
				new Task("Lab #4", 2, 2),
				new Task("Lab #5", 2, 2),
				new Task("Lab #6", 2, 2),
				new Task("Lab #7", 4, 4)
		};
		
		//array of midterms for the 2 midterms
		Task[] Midterms = {
				new Task("Midterm Exam #1", 10, 10),
				new Task("Midterm Exam #2", 10, 10)
		};
		
		//task object for final exam
		Task FinalExam = new Task ("Final Exam", 20, 20);
		
		//these 3 ints are each for the number of practice problems, labs and midterms completed as of the time the user runs this program
		int completedPP, completedLabs, completedMidterms; 

		int score = 0; //this is an int which will be used to store the amount of points earned on a certain assignment
		
		//this int is a sum variable, the amount of points received per assignment will be summed up, which will be the user's current grade
		int currentGrade = 0;
		
		//if the user has not taken their final exam as of the time of running this program, this boolean variable will remain false
		boolean didFinal = false;
		
		System.out.println("CISC3130: Data Structures and Algorithms\n\t  Grade Calculator\n"); //output header
		
		//while loop that will take user input
		System.out.println("How many practice problems have you completed?");
		while(input.hasNext()) {
			//the user will answer this question with an int, the practice problems array will be iterated through that many times
			completedPP = input.nextInt(); 
			
			//the grade loop will ask what the grade for each completed assignment was
			//the grade uses the task objects setter method to set that grade
			//the grade received will also be added to the currentGrade variable
			for (int i = 0; i < completedPP; i++) {
				System.out.println("How much did you get on Practice Problems #" + (i+1) + "?");
				score = input.nextInt();
				PracticeProblems[i].setMyScore(score);
				currentGrade += PracticeProblems[i].getMyScore();
			} //end of for
			
			//the next two loops will do the exact same as the previous except that they're for Labs and Midterms
			System.out.println("How many labs have you completed?");
			completedLabs = input.nextInt();
			
			for (int i = 0; i < completedLabs; i++) {
				System.out.println("How much did you get on Lab #" + (i+1) + "?");
				score = input.nextInt();
				Labs[i].setMyScore(score);	
				currentGrade += Labs[i].getMyScore();
			} //end of for
			
			System.out.println("How many midterms have you completed?");
			completedMidterms = input.nextInt();
			
			for (int i = 0; i < completedMidterms; i++) {
				System.out.println("How much did you get on Midterm #" + (i+1) + "?");
				score = input.nextInt();
				Midterms[i].setMyScore(score);	
				currentGrade += Midterms[i].getMyScore();
			} //end of for
			
			//after the two loops are done the user is asked if they have taken the final
			//if they have they'll be prompted to enter their score on the final
			//after that their overall grade will be printed and the program ends
			System.out.println("Have you completed the final?");
			String next = input.next();
			
			if (next == "yes" || next == "Yes") {
				didFinal = true;
				score = input.nextInt();
				FinalExam.setMyScore(score);
				currentGrade += FinalExam.getMyScore();
				System.out.println("Your grade is: " + currentGrade + "/100");
				break;
			} //end of if
			
			//if the user has not taken the final then their current grade is printed, then a method called gradeNeeded is called
			//the gradeNeeded method calculates how much is needed to get a grade of A in the course as well as remaining assignments
			//it creates a String which will contain all of this info and then return it which is then printed to the console and the 
			//program ends
			else {
				System.out.println("Your current grade is: " + currentGrade + "/100");
				String gradeNeeded = gradeNeeded(currentGrade, PracticeProblems, completedPP, Labs, completedLabs, Midterms, completedMidterms);
				System.out.println(gradeNeeded);
				break;
			} //end of else
		} //end of while
	} //end of main method
	
	//this is the gradeNeeded that calculates remaining score and what's needed to get an A
	private static String gradeNeeded(int current, Task[] PP, int compPP, Task[] Labs, int compLabs, Task[] Midterms, int compMidterms) {
		//takes the current grade, the 3 task arrays and how many of each type of task has been completed as arguments
		int A = 90; //int A is a 90, ie the letter grade A
		int needed = A-current; //90 - current is the current amount needed to get an A
		
		String gradeNeeded = "";
		
		/* the following if statements check if the all the task of that category were completed, if they weren't then the amount that's 
		 * left is calculated and the most possible score that can be received from them is added to a sum variable, then the information
		 * of how many of those types of assigments are remaining and how many points can be received from them are added to a String */
		
		if (compPP != PP.length) {
			gradeNeeded += "You need to complete the remaining " + (PP.length-compPP) + " Practice Problems which is worth a total of ";
			int sumNeedPP = 0;
			for (int i = compPP; i < PP.length; i++) {
				needed -= PP[i].getPossiblePoints();
				sumNeedPP += PP[i].getPossiblePoints();
			} //end of for
			current += sumNeedPP; //adding how much the grade will increase if the remaining practice problems are completed
			gradeNeeded += sumNeedPP + " points and will bring your grade up to " + current + "/100.\n";
		}
		
		if (compLabs != Labs.length) {
			gradeNeeded += "You need to complete the remaining " + (Labs.length-compLabs) + " labs which is worth a total of ";
			int sumNeedLabs = 0;
			for (int i = compLabs; i < Labs.length; i++) {
				needed -= Labs[i].getPossiblePoints();
				sumNeedLabs += Labs[i].getPossiblePoints();
			} //end of for
			current += sumNeedLabs; //adding how much the grade will increase if the remaining labs are completed
			gradeNeeded += sumNeedLabs + " points and will bring your grade up to " + current + "/100\n";
		}
		
		if (compMidterms != Midterms.length) {
			gradeNeeded += "You need to complete the remaining " + (Midterms.length-compMidterms) + "Midterms which is worth a total of ";
			int sumNeedMidterms = 0;
			for (int i = compMidterms; i < Midterms.length; i++) {
				needed -= Midterms[i].getPossiblePoints();
				sumNeedMidterms += Midterms[i].getPossiblePoints(); 
			} //end of for
			current += sumNeedMidterms; //adding how much the grade will increase if the remaining midterms are completed
			gradeNeeded += sumNeedMidterms + " points and will bring your grade up to " + current + "/100\n";
		}
		
		/* in each of the if the statements, if they were executed subtract from the needed variable the amount the remaining points would 
		 * decrease from the amount needed to get an A upon their completion, thus the needed variable after all the if statements will be 
		 * the amount that the user needs to get on their final exam to pass this course with an A. That information is then added to the 
		 * gradeNeeded String. */
		
		gradeNeeded += "You will need to get at least " + needed + "/20 on the final to get an A.";
		
		/* the gradeNeeded String was updated in all of the if statements, containing all the informatuon regarding how many assignments were
		 * remaining and how many points they'll add to the grade upon completion and lastly the amount needed to get on the final to get an A,
		 * then this String is returned to the main method where it was originally called.*/
		
		return gradeNeeded;	
	} //end of method 
} //end of class