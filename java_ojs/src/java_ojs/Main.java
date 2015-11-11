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
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Main {
	JFrame f, g;
	private JTextField api_url;
	private JTextField access_key;
	private JTable table;

	/*
	 * Initial setup test
	 */
	public Main() {
		g = new JFrame();
		g.setSize(640, 480);
		g.getContentPane().setBackground(new Color(128, 128, 128));
		g.setVisible(false);
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "View" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3", "View" },
				{ "Row3-Column1", "Row3-Column2", "Row3-Column3", "View" },
				{ "Row4-Column1", "Row4-Column2", "Row4-Column3", "View" } };
		Object columnNames[] = { "Column One", "Column Two", "Column Three", "" };
		g.getContentPane().setLayout(null);
		final Button internet = new Button("ONLINE");
		internet.setForeground(Color.WHITE);
		internet.setFont(new Font("Arial", Font.BOLD, 12));

		internet.setBounds(520, 20, 70, 25);
		internet.setBackground(Color.GREEN);
		g.getContentPane().add(internet);

		final JButton btnSync = new JButton("Sync");
		btnSync.setBounds(445, 20, 70, 25);
		g.getContentPane().add(btnSync);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 80, 400, 280);
		g.getContentPane().add(scrollPane);
		table = new JTable(rowData, columnNames);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		table.setCellSelectionEnabled(true);
		int delay = 500; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress("www.youtube.com", 80);
					sock.setSoTimeout(500);
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
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		Action view = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JOptionPane.showMessageDialog(null, modelRow);
				// / ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, view, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		f = new JFrame();
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
		Action actionSubmit = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	String url = api_url.getText();
				String key = access_key.getText();
				if (key.compareTo("enter") == 0 && url.compareTo("api") == 0) {
					f.setVisible(false);
					g.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong access key or API url");
				}
		    }
		};
		access_key.addActionListener(actionSubmit);
		api_url.addActionListener(actionSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = api_url.getText();
				String key = access_key.getText();
				if (key.compareTo("enter") == 0 && url.compareTo("api") == 0) {
					f.setVisible(false);
					g.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong access key or API url");
				}

			}
		});

		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setBounds(139, 300, 117, 29);
		f.getContentPane().add(btnSubmit);

		final Button internet1 = new Button("ONLINE");
		internet1.setForeground(Color.WHITE);
		internet1.setFont(new Font("Arial", Font.BOLD, 12));

		internet1.setBounds(311, 60, 70, 25);
		internet1.setBackground(Color.GREEN);
		f.getContentPane().add(internet1);

		final JButton btnSync1 = new JButton("Sync");
		btnSync1.setBounds(235, 60, 70, 25);
		f.getContentPane().add(btnSync1);
		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);

					internet1.setBackground(Color.GREEN);
					internet1.setLabel("ONLINE");
					btnSync1.setEnabled(true);

				} catch (Exception e) {
					internet1.setBackground(Color.RED);
					internet1.setLabel("OFFLINE");
					btnSync1.setEnabled(false);
				}
			}
		};
		new Timer(delay, taskPerformer1).start();
		f.setVisible(true);// making the frame visible

	}

	public static void main(String[] args) {
		new Main();
	}
}