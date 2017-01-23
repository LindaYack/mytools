package huimin.gongju;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class XmlGetStrings extends JFrame {

	private static final long serialVersionUID = -712900085069910303L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textAreaResult;
	private String modal = "insert into `zhufuyu` (`content`,`userid`,`status`,`bigtypeid`,`smalltypeid`) values('<content>',1,1,1,1);";
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XmlGetStrings frame = new XmlGetStrings();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XmlGetStrings() {
		setTitle("XML\u63D0\u53D6\u5B57\u7B26\u4E32\u7A0B\u5E8F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JLabel lblNewLabel = new JLabel("\u8F93\u5165xml\uFF1A");
		panel_2.add(lblNewLabel);

		textArea = new JTextArea();
		JScrollPane jscrollPane = new JScrollPane(textArea);
		panel_2.add(jscrollPane);
		textArea.setColumns(40);
		textArea.setRows(12);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		JLabel lblNewLabel2 = new JLabel("\u7ED3\u679C\uFF1A");
		panel_1.add(lblNewLabel2);

		textAreaResult = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaResult);
		panel_1.add(scrollPane);
		textAreaResult.setColumns(40);
		textAreaResult.setRows(12);

		JButton btnNewButton = new JButton("\u751F\u6210");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modal = textField.getText();
				new Thread() {
					@Override
					public void run() {
						super.run();
						String xmlInput = textArea.getText();
						StringBuffer sbresult = new StringBuffer();
						Pattern pat = Pattern.compile("<div class=\"textbox\">[^</>]+</div>");
						Matcher m = pat.matcher(xmlInput);
						List<String> norepeat = new ArrayList<String>();
						while (m.find()) {
							String result = m.group();
							if (!norepeat.contains(result)) {
								norepeat.add(result);
//								System.out.println(modal);
//								System.out.println(result);
								String quotestrings=Matcher.quoteReplacement(result.substring(21, result.length() - 6).replaceAll("\'", "‘"));
								sbresult.append(modal.replaceAll("<content>",quotestrings)).append("\n");
							}
						}
						textAreaResult.setText(sbresult.toString());
					}

				}.start();
			}
		});

		JPanel panel_3 = new JPanel(new BorderLayout());
		contentPane.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("插入的字符，用'<content>'做待插入占位符：");
		panel_3.add(lblNewLabel_1, BorderLayout.WEST);

		textField = new JTextField();
		textField.setText(modal);
		panel_3.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
}
