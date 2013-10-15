package com.iprange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

public class MainFrame extends JFrame {
	private int screenHeight;
	private int screenWidth;
	private JTextField startIpField;
	private JTextField endIpField;
	private JTextArea resultArea;

	MainFrame() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		screenHeight = toolkit.getScreenSize().height;
		screenWidth = toolkit.getScreenSize().width;
		setSize(screenWidth / 4, screenHeight / 2);
		setMinimumSize(new Dimension(screenWidth / 4, screenHeight / 2));
		setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 2));
		setLocationRelativeTo(null);
		setTitle("Show ip range");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container mainContainer = getContentPane();
		GroupLayout layout = new GroupLayout(mainContainer);
		mainContainer.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		JPanel infoPanel = createInfoPanel();
		JPanel workPanel = createWorkPanel();
		JPanel resultPanel = createResultPanel();
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(infoPanel)
						.addComponent(workPanel)
						.addComponent(resultPanel)
				));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(infoPanel)
				.addComponent(workPanel)
				.addComponent(resultPanel)
				);

	}

	public JPanel createInfoPanel() {
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 20));
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createEtchedBorder());
		JTextArea infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setFocusable(false);
		infoArea.setBackground(Color.WHITE);
		infoArea.setText("Enter the start and end IPs to the corresponding fields. \n" +
				"IPs should have format like 255.255.255.255");
		panel.add(infoArea, BorderLayout.CENTER);
		return panel;
	}

	public JPanel createWorkPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.WHITE);
		panel.setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 10));
		JLabel startIpLabel = new JLabel();
		startIpLabel.setText("Enter start IP");
		JLabel endIpLabel = new JLabel();
		endIpLabel.setText("Enter end IP");
		startIpField = new JTextField();
		startIpField.setText("0.0.0.0");
		endIpField = new JTextField();
		endIpField.setText("0.0.0.10");
		JButton resultButton = new JButton();
		resultButton.setText("Press to show IPs in range");
		resultButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resultArea.setText("");
				ResultProccess rp = new ResultProccess();
				rp.execute();
			}
		});

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(startIpLabel)
										.addComponent(endIpLabel))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(startIpField)
										.addComponent(endIpField)))
						.addComponent(resultButton)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(startIpLabel)
						.addComponent(startIpField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(endIpLabel)
						.addComponent(endIpField))
				.addComponent(resultButton));

		return panel;

	}

	public JPanel createResultPanel() {
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 2));
		panel.setBorder(BorderFactory.createEtchedBorder());
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setFocusable(true);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	private class ResultProccess extends SwingWorker<Object, Object> {

		private List<String> resultList;

		@Override
		protected Object doInBackground() throws Exception {
			IpRange iprange = new IpRange();
			resultList = iprange.proccessIpRange(startIpField.getText(), endIpField.getText());
			return null;
		}

		@Override
		protected void done() {
			for (String s : resultList) {
				resultArea.append(s);
				resultArea.append("\n");
			}
			resultArea.revalidate();
			resultArea.repaint();
		}
	}

}
