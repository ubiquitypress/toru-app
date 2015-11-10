package java_ojs;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;  
public class Main {  
JFrame f;  
private JTextField api_url;
private JTextField access_key;
/*
 * Initial setup test
 */
public Main(){  
f=new JFrame();
f.getContentPane().setBackground(new Color(128, 128, 128));
          
f.setSize(400,500);//400 width and 500 height  
f.getContentPane().setLayout(null);//using no layout managers  
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
f.setVisible(true);//making the frame visible  
}  
  
public static void main(String[] args) {  
new Main();  
}  
}  