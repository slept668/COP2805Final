package cop2805;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame{
	public JTextArea preEncryptTA;
	public JTextArea postEncryptTA;
	public JButton fileChooser;
	public JButton run;
	public JFileChooser mainFileChooser;
	
	
	public MainWindow() {
		super();
		init();
	}
	
	private void init() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Super Secret Message Encrypter");
		this.setLayout(new GridLayout(3, 3));
		
		this.add(new JLabel("Message To Encrypt"));
		preEncryptTA = new JTextArea();
		JScrollPane preScrollPane = new JScrollPane(preEncryptTA);
		this.add(preScrollPane);
		preEncryptTA.setEditable(false);
		preEncryptTA.setLineWrap(true);
		
		this.add(new JLabel("Encrpyted Message"));
		postEncryptTA = new JTextArea();
		JScrollPane postScrollPane = new JScrollPane(postEncryptTA);
		this.add(postScrollPane);
		postEncryptTA.setEditable(false);
		postEncryptTA.setLineWrap(true);
		
		fileChooser = new JButton("Choose File");
		fileChooser.addActionListener(new FileExplorer(this));
		this.add(fileChooser);
		run = new JButton("Encrypt");
		run.addActionListener(new RunListener(this));
		this.add(run);
		
		int frameWidth = 450;
		int frameHeight = 250;
		Dimension screenSize =
		Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int) screenSize.getWidth()/2 - (int) frameWidth/2, (int) screenSize.getHeight()/2 - (int) frameHeight/2,
		frameWidth, frameHeight);
		this.setVisible(true);
		}
}

class RunListener implements ActionListener{
	MainWindow fr;
	
	public RunListener(MainWindow frame) {
		fr = frame;
	}

	public void actionPerformed(ActionEvent eventData) {
		try {
			String fullString;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("An unexpected error occured");
		}
	}	
}

class FileExplorer implements ActionListener{
	MainWindow fr;
	
	public FileExplorer(MainWindow frame) {
		fr = frame;
	}

	public void actionPerformed(ActionEvent eventData) {
		try {
			List<String> fileStrings = new ArrayList<String>();
			String fullString = "";
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			Path filePath = selectedFile.toPath();
			try {
				fileStrings = Files.readAllLines(filePath);
				
				for (String item : fileStrings) {
					fullString += item;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			fr.preEncryptTA.setText(fullString);
			System.out.println(selectedFile.getName());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("An unexpected error occured");
		}
	}	
}
