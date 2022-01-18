/**
 * 
 * @author Kristan Jay T. Gabay
 * Singleton design pattern for storing the scores and musical sheets
 *
 */
public class singletonScore {
 
    private static singletonScore instance = new singletonScore(); // initialize the only instance of this class
    // these are the keys you need to press to score in each song
    private static String goal1 = "aegegegermegegegqwnegegegrgbtgrgegwvqgqgqgqwcqgqgqgtxqgqwewewqgbqgqwewewqw";
    private static String goal2 = "qwvetbrecwqveghvqwbejcqgvhqwveybtecwqveghvqwbejcqgvhvqtqtvqvqtqtvqnqtqtnqbqwqeqvqtqtvqvqtqtqnqtqtqbqutbyetwvqwqeqqwqeqbqwqeq";
    // acts as an index when accessing the mSheet and the goal
    private int counter1;
    private int counter2;
    
    public int highScore;// stores the high score
    public int score; // stores the score
    
    // every button you press is stored in these two
    private StringBuilder mSheet1 = new StringBuilder();
    private StringBuilder mSheet2 = new StringBuilder();
    
    
     
    
    /**
     * This private constructor is to prevent this object get instantiated more than once.
     */
    private singletonScore(){} // private constructor so that this class can't be directly instantiated 
    
    /**
     * 
     * @return the single instance of this class
     */
    public static singletonScore getInstance() // when instantiating this class, returns the single instance of this class
    {
        return instance;
    }
    
    /**
     * method that increments the scores
     * @param c the string that will be appended 
     * @param songNum the song that is currently being scored
     */
    public void incrementScore(String c, int songNum)
    { 
    	if(songNum == 2) {
    		this.mSheet1.append(c);    	// appends your button presses into the string builder mSheet1
            int modCounter = counter1 % 74; // this acts as the index for the goal. this ensures that even if you have played the entirety of the song, you can start from the start and continue scoring
            
            if (this.goal1.charAt(modCounter)==this.mSheet1.charAt(counter2)) {
            	// when the character of goal at index modCounter is the same as the character of mSheet at index counter2, increment score, counter1 and 2
            	this.score++;
            	this.counter1++;
            	this.counter2++;
            	if(this.score>this.highScore) {
            		// updates the high score if score is greater than the high score
            		this.highScore = this.score;
            	}
            }
            else {
            	// if there is no match, increment counter2 only and continue matching until the character if mSheet at index counter 2 is the same as the char of goal at char modCounter
            	this.counter2++;
            	this.score--; // decrement when wrong char is pressed
            }   
    	}
    	if(songNum == 3) {
    		this.mSheet2.append(c);
            int modCounter = counter1 % 74;
            if (this.goal2.charAt(modCounter)==this.mSheet2.charAt(counter2)) {
            	this.score++;
            	this.counter1++;
            	this.counter2++;
            	if(this.score>this.highScore) {
            		this.highScore = this.score;
            	}
            }
            else {
            	this.counter2++;
            	this.score--;
            }   
    	}
        
    }
    /**
     * method that resets the incrementing variables to 0
     */
    public void reset() {
    	this.counter1 = 0;
    	this.counter2 = 0;
    	// create new instance of string builder to clear string
    	mSheet1 = new StringBuilder();
        mSheet2 = new StringBuilder();
    	this.score = 0;
    	this.highScore = 0;
    	
    }
     
}
