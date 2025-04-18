package cop2805;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame{
	public JTextArea preDecryptTA;
	public JTextArea postDecryptTA;
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
		
		this.add(new JLabel("Encrypted Message"));
		preDecryptTA = new JTextArea();
		JScrollPane preScrollPane = new JScrollPane(preDecryptTA);
		this.add(preScrollPane);
		preDecryptTA.setEditable(false);
		preDecryptTA.setLineWrap(true);
		
		this.add(new JLabel("Decrpyted Message"));
		postDecryptTA = new JTextArea();
		JScrollPane postScrollPane = new JScrollPane(postDecryptTA);
		this.add(postScrollPane);
		postDecryptTA.setEditable(false);
		postDecryptTA.setLineWrap(true);
		
		fileChooser = new JButton("Choose File");
		fileChooser.addActionListener(new FileExplorer(this));
		this.add(fileChooser);
		run = new JButton("Decrypt");
		run.addActionListener(new RunListener(this));
		this.add(run);
		
		int frameWidth = 900;
		int frameHeight = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int) screenSize.getWidth()/2 - (int) frameWidth/2, (int) screenSize.getHeight()/2 - (int) frameHeight/2,
		frameWidth, frameHeight);
		this.setVisible(true);
		}
}

class RunListener implements ActionListener{
	MainWindow fr;
	String preTAString = null;
	
	public RunListener(MainWindow frame) {
		fr = frame;
	}

	public void actionPerformed(ActionEvent eventData) {
		try {
			preTAString = fr.preDecryptTA.getText();
			if (preTAString.isEmpty()) {
				JOptionPane.showMessageDialog(null, "NO DATA TO TRANSMIT", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					Socket socket = new Socket("127.0.0.1", 668);
					
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					writer.println(preTAString);
					writer.flush();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					String serverResponse = reader.readLine();
					
					fr.postDecryptTA.setText(serverResponse);
					
					System.out.println(serverResponse);
					
					socket.close();
					
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(null, "CANNOT CONNECT TO HQ. TAKE THE MOLAR PILL.", "WARNING", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "CANNOT CONNECT TO HQ. TAKE THE MOLAR PILL.", "WARNING", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}
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
			fr.preDecryptTA.setText(fullString);
			System.out.println(selectedFile.getName());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("An unexpected error occured");
		}
	}	
}
