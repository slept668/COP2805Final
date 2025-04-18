package cop2805;

import javax.swing.SwingUtilities;

public class FieldAgentClient {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			MainWindow mainWindow = new MainWindow();
			}
			});
	}

}
