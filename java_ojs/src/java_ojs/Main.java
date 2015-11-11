package java_ojs;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Main {
	JFrame f;
	private JTextField api_url;
	private JTextField access_key;

	/*
	 * Initial setup test
	 */
	public Main() {

		f = new JFrame();
		f.getContentPane().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		f.getContentPane().setBackground(new Color(128, 128, 128));

		f.setSize(400, 500);// 400 width and 500 height
		f.getContentPane().setLayout(null);// using no layout managers
		api_url = new JTextField();
		api_url.setBounds(80, 195, 239, 26);
		f.getContentPane().add(api_url);
		api_url.setColumns(10);
		JLabel lblApi = new JLabel("API");
		lblApi.setForeground(new Color(245, 255, 250));
		lblApi.setHorizontalAlignment(SwingConstants.CENTER);
		lblApi.setBounds(80, 179, 239, 16);
		f.getContentPane().add(lblApi);
		JPanel title_background = new JPanel();
		title_background.setBackground(new Color(0, 0, 0));
		title_background.setBounds(-17, 0, 433, 54);
		f.getContentPane().add(title_background);
		JLabel lblNewLabel = new JLabel("TORU");
		title_background.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBackground(new Color(230, 230, 250));
		lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
		lblNewLabel.setToolTipText("Welcome\n");
		access_key = new JTextField();
		access_key.setColumns(10);
		access_key.setBounds(80, 248, 239, 26);
		f.getContentPane().add(access_key);
		JLabel lblAccessKey = new JLabel("Access Key");
		lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccessKey.setForeground(new Color(245, 255, 250));
		lblAccessKey.setBounds(80, 233, 239, 16);
		f.getContentPane().add(lblAccessKey);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setBounds(139, 300, 117, 29);
		f.getContentPane().add(btnSubmit);

		final Button internet = new Button("ONLINE");
		internet.setForeground(Color.WHITE);
		internet.setFont(new Font("Arial", Font.BOLD, 12));
		
		internet.setBounds(29, 79, 70, 23);
		internet.setBackground(Color.GREEN);
		f.getContentPane().add(internet);
		
		final JButton btnSync = new JButton("Sync");
		btnSync.setBounds(105, 77, 70, 25);
		f.getContentPane().add(btnSync);
	    int delay = 1000; //milliseconds
	      ActionListener taskPerformer = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	      		try {
	      			Socket sock = new Socket();
	      			InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
	      			
	    			sock.connect(addr, 3000);
	    			
	    			internet.setBackground(Color.GREEN);
	    			internet.setLabel("ONLINE");
	    			btnSync.setEnabled(true);
	    	
	    		} catch (Exception e) {
	    			internet.setBackground(Color.RED);
	    			internet.setLabel("OFFLINE");
	    			btnSync.setEnabled(false);
	    		}
	          }
	      };
	      new Timer(delay, taskPerformer).start();
		f.setVisible(true);// making the frame visible
	}

	public static void main(String[] args) {
		new Main();
	}
}