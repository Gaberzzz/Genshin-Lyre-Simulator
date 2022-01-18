import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * 
 * @author Kristam Jay T. Gabay
 * This is a fan made Genshin Impact Lyre simulator and is intended for educational purposes only. No copyright infringement intended
 */
public class genshinLyreSimulator extends JFrame{	
	/**
	 * Program lets you play the Genshin Impact Lyre without having the hassle of opening Genshin Impact and wait for the game to load to play the lyre there.	
	 */
	private static final long serialVersionUID = -7310475430433904957L;
	
	// Stores the dimensions of the buttons
	private static final int buttonHeight = 100; 
	private static final int buttonWidth = 100;
	private static final int resetButtonHeight = 50;
	private static final int resetButtonWidth = 300;
	private static final int songMenuHeight = 40;
	private static final int songMenuWidth = 210;
	
	// displays the score and instructions of the game 
	private static JLabel score;
	private static JLabel instructions;
	private static URL soundURL;  // a pointer that accesses the .wav files in the resource folder

	private static int songNumber; // holds the number of the song that is being scored. 0 if there's no scoring
	
	
	/**
	 * Singleton class for storing the score, high score, and the musical sheets that will be used to compare with your input to generate a score 
	 */
	private singletonScore singleton = singletonScore.getInstance();
	
	/**
	 * Main method that creates an instance of the simulator
	 * @param args array of string arguments
	 */
	public static void main(String[] args) {
		
		new genshinLyreSimulator(); //creates instance of the lyre
	}
	/**
	 * initializes the GUI of the simulator
	 */
	private genshinLyreSimulator() {
		initPanel();
        initButtons();   
        initFrame();
	}
	
	/**
	 * initializes the panel and labels using JPanel and JLabel
	 */
	private void initPanel() {
		
		// initialize panel
		JPanel contentPane = new JPanel();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 255)); 
        contentPane.setLayout(null);
        
        // initializes the label 
        instructions = new JLabel("You are in free play. Choose a song from the left to score points");
        instructions.setForeground(new Color(255, 255, 0));
        instructions.setHorizontalAlignment(SwingConstants.CENTER); 
        instructions.setFont(new Font("Century Gothic", Font.BOLD, 20));
        instructions.setBounds(0, -285, 1125, 625);
        contentPane.add(instructions);
       
        // initializes label for showing score and high score
        score = new JLabel("Score is: "+singleton.score+"                                                                         High score is: "+singleton.highScore);
        score.setForeground(new Color(255, 255, 0));
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(new Font("Century Gothic", Font.BOLD, 20));
        score.setBounds(0, -230, 1125, 625);
        contentPane.add(score);
        setContentPane(contentPane);
		
	}
	/**
	 * initializes the buttons that will play the musical notes when triggered
	 */ 
	private void initButtons() {
		
		// takes the key stroke "1" which will trigger the freePlayBtn 
        KeyStroke key00 = KeyStroke.getKeyStroke(KeyEvent.VK_1, 0);
        JButton freePlayBtn = song(5,5,1,key00,"Free play press 1","You are in free play. Choose a song from the left to score points");
        add(freePlayBtn);
        
        // takes the key stroke "2" which will trigger the song1 button which will start the program to record the score when playing that song
        KeyStroke key0 = KeyStroke.getKeyStroke(KeyEvent.VK_2, 0);
        JButton song1 = song(5,50,2,key0,"Play Dragonspine press 2","Play Dragonspine OST to score points");
        add(song1);
        
        // takes the key stroke "3" which will trigger the song2 button which will start the program to record the score when playing that song
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_3, 0);
        JButton song2 = song(5,95,3,key,"Play Mitsuha's Theme press 3","Play Mitsuha's Theme to score points");
        add(song2);
        
        // keys 1 - 21 and buttons 1 to 21 will play a musical note from genshin impact when the corresponding key is pressed
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0);
        JButton button1 = initButton(key1, "DO (Q)", "do1.wav", 50, 150);

        KeyStroke key2 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        JButton button2 = initButton(key2, "RE (W)", "re1.wav", 200, 150);

        KeyStroke key3 = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0);
        JButton button3 = initButton(key3, "MI (E)", "mi1.wav", 350, 150);

        KeyStroke key4 = KeyStroke.getKeyStroke(KeyEvent.VK_R, 0);
        JButton button4 = initButton(key4, "FA (R)", "fa1.wav", 500, 150);

        KeyStroke key5 = KeyStroke.getKeyStroke(KeyEvent.VK_T, 0);
        JButton button5 = initButton(key5, "SO (T)", "so1.wav", 650, 150);

        KeyStroke key6 = KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0);
        JButton button6 = initButton(key6, "LA (Y)", "la1.wav", 800, 150);

        KeyStroke key7 = KeyStroke.getKeyStroke(KeyEvent.VK_U, 0);
        JButton button7 = initButton(key7, "TI (U)", "ti1.wav", 950, 150);

        KeyStroke key8 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        JButton button8 = initButton(key8, "DO (A)", "do2.wav", 50, 300);

        KeyStroke key9 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        JButton button9 = initButton(key9, "RE (S)", "re2.wav", 200, 300);

        KeyStroke key10 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        JButton button10 = initButton(key10, "MI (D)", "mi2.wav", 350, 300);

        KeyStroke key11 = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0);
        JButton button11 = initButton(key11, "FA (F)", "fa2.wav", 500, 300);

        KeyStroke key12 = KeyStroke.getKeyStroke(KeyEvent.VK_G, 0);
        JButton button12 = initButton(key12, "SO (G)", "so2.wav", 650, 300);

        KeyStroke key13 = KeyStroke.getKeyStroke(KeyEvent.VK_H, 0);
        JButton button13 = initButton(key13, "LA (H)", "la2.wav", 800, 300);

        KeyStroke key14 = KeyStroke.getKeyStroke(KeyEvent.VK_J, 0);
        JButton button14 = initButton(key14, "TI (J)", "ti2.wav", 950, 300);

        KeyStroke key15 = KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0);
        JButton button15 = initButton(key15, "DO (Z)", "do3.wav", 50, 450);

        KeyStroke key16 = KeyStroke.getKeyStroke(KeyEvent.VK_X, 0);
        JButton button16 = initButton(key16, "RE (X)", "re3.wav", 200, 450);

        KeyStroke key17 = KeyStroke.getKeyStroke(KeyEvent.VK_C, 0);
        JButton button17 = initButton(key17, "MI (C)", "mi3.wav", 350, 450);

        KeyStroke key18 = KeyStroke.getKeyStroke(KeyEvent.VK_V, 0);
        JButton button18 = initButton(key18, "FA (V)", "fa3.wav", 500, 450);

        KeyStroke key19 = KeyStroke.getKeyStroke(KeyEvent.VK_B, 0);
        JButton button19 = initButton(key19, "SO (B)", "so3.wav", 650, 450);

        KeyStroke key20 = KeyStroke.getKeyStroke(KeyEvent.VK_N, 0);
        JButton button20 = initButton(key20, "LA (N)", "la3.wav", 800, 450);

        KeyStroke key21 = KeyStroke.getKeyStroke(KeyEvent.VK_M, 0);
        JButton button21 = initButton(key21, "TI (M)", "ti3.wav", 950, 450);
        
        KeyStroke key22 = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
        JButton resetButton = resetButton(400,60,"RESET (backspace)","Back Space",key22);
     
        // adds the buttons to the JFrame by directly using the add() method since JFrame is this class' superclass
		add(resetButton);
        add(button21);
        add(button20);
        add(button19);
        add(button18);
        add(button17);
        add(button16);
        add(button15);
        add(button14);
        add(button13);
        add(button12);
        add(button11);
        add(button10);
        add(button9);
        add(button8);
        add(button7);
        add(button6);
        add(button5);
        add(button4);
        add(button3);
        add(button2);
        add(button1);
	}
	
	/**
	 *  initializes the properties of the JFrame
	 */
	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets the program be closed when the top right X is pressed
        setTitle("Genshin Impact Lyre Simulator"); // name of the window
		setSize(1125,625); // dimensions of the window
		setResizable(false); // disables resizing
		setLocationRelativeTo(null); // to spawn the window at the center of the screen
		setVisible(true); // set visibility to true 
	}
	

	/**
	 * Method that instantiates a button with the following properties
	 * @param x position of button in the x coordinate
	 * @param y position of button in the y coordinate
	 * @param btnName or the name of the button
	 * @param keyPress string name of the button
	 * @param key is the corresponding key press
	 * @return the modified button
	 */
	private JButton resetButton(int x, int y,String btnName, String keyPress, KeyStroke key) {
		JButton resetButton = new JButton();
		resetButton.setText(btnName); // sets the name of the button
		resetButton.setBounds(x, y, resetButtonWidth, resetButtonHeight); // set width and height using global variables

		// initializes the AbstractAction class to read the key press and perform certain actions
        AbstractAction aa = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                	// since when pressing buttons with hotkeys doesn't display the button being pressed, 
                	resetButton.getModel().setArmed(true); // setArmed and setPressed methods changes the button to look like it is pressed
                    resetButton.getModel().setPressed(true);
                    Timer t = new Timer(0, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            resetButton.getModel().setArmed(false); // after 0, button will look like into its former unpressed state
                            resetButton.getModel().setPressed(false);
                        }
                    });
                    t.setRepeats(false); // stops the timer from doing the actions above again
                    t.start(); // starts the action and the timer 
                	singleton.reset(); // since this is the reset button, this part resets the variables that are inside the singleton class
                	// displays the new values
                	score.setText("Score is: "+singleton.score+"                                                                         High score is: "+singleton.highScore);
        			}
        		catch(Exception ex){
        			// prints the error
        			ex.printStackTrace();
        		}

                resetButton.requestFocusInWindow();//request that the button has focus
            }
        };	
        
        resetButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, keyPress); // sets the key needed to trigger the button
        resetButton.getActionMap().put(keyPress, aa); // adds the key and the action into the button
        resetButton.addActionListener(aa);//so that the button can be clicked
        return resetButton;
	}
	/**
	 * instantiates the buttons that chooses what song you want to be scored.
	 * @param x position of button in the x coordinate
	 * @param y position of button in the y coordinate
	 * @param songNum the song number 
	 * @param key is the corresponding key press 
	 * @param btnName name of the button
	 * @param newLabel changes the JLabel instructions  with this string
	 * @return the modified button
	 */
	private JButton song(int x, int y,int songNum, KeyStroke key,String btnName, String newLabel) {
		JButton song = new JButton();
		song.setText(btnName);
		song.setBounds(x, y, songMenuWidth, songMenuHeight);
		String keyPress = Integer.toString(songNum); //converts Integer to String 

        AbstractAction aa = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                	song.getModel().setArmed(true); 
                    song.getModel().setPressed(true);
                    Timer t = new Timer(0	, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            song.getModel().setArmed(false);
                            song.getModel().setPressed(false);
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                    instructions.setText(newLabel);
                    songNumber = songNum;
                    singleton.reset();
                	score.setText("Score is: "+singleton.score+"                                                                         High score is: "+singleton.highScore);

        			}
        		catch(Exception ex){
        			ex.printStackTrace();
        		}

                song.requestFocusInWindow();
            }
        };	
        song.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, keyPress); 
        song.getActionMap().put(keyPress, aa);
        song.addActionListener(aa);
        return song;
	}


	/**
	 * this method initializes all the buttons the produces a sound when pressed
	 * @param key the key stroke that will trigger the button
	 * @param note name of the button 
	 * @param fileName name of the .wav file 
	 * @param x position of button in the x coordinate
	 * @param y position of button in the y coordinate
	 * @return the modified button
	 */
	private JButton initButton(KeyStroke key, String note, String fileName, int x, int y) {
		JButton button = new JButton(note);
		button.setBounds(x, y, buttonHeight, buttonWidth); // x and y is the button's coordinates in the window
        AbstractAction aa = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                	button.getModel().setArmed(true);
                    button.getModel().setPressed(true);
                    Timer t = new Timer(0, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            button.getModel().setArmed(false);
                            button.getModel().setPressed(false);
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                	String c = ae.getActionCommand(); //gets the String of the command
                	incrementScore(c,songNumber); // gets passed into the singleton class where "c" is appended to a variable called mSheet1 or mSheet2 depends on the song
                	playMusic(fileName); // plays the music 
                	System.out.print(c);
        			}
                	
        		catch(Exception ex){
        			ex.printStackTrace();
        		}
                button.requestFocusInWindow(); 
            }    
        };	
        
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, note);
        button.getActionMap().put(note, aa);
        button.addActionListener(aa);
        
        return button;
	}

	/**
	 * method that updates the score inside the singleton class
	 * @param c the string of your key press
	 * @param songNum the song number being scored at run time
	 */
	private void incrementScore(String c, int songNum) {
		
		singleton.incrementScore(c,songNum);
    	// changes the label to show updated scores
    	score.setText("Score is: "+singleton.score+"                                                                         High score is: "+singleton.highScore);
    }
	/**
	 * method that takes the file from the resource Sound Files folder and plays it
	 * @param fileName name of the file ex. "do1.wav" 
	 * @throws LineUnavailableException throws an exception when the line that contains the fileName is not available
	 * @throws UnsupportedAudioFileException throws an exception when the file type is unsupported
	 * @throws IOException throws an exception when there are input/output errors
	 */
	private void playMusic(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		soundURL = getClass().getResource(fileName); // gets the location of the wav files inside the resource folder
		try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);){ 
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
	}
}
