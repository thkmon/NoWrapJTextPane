package com.thkmon.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class MainFrame extends JFrame {

	private JTextPane textpane;
	private JTextPane lineNumbers;

	public MainFrame() {
		setTitle("JFrame");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textpane = new JTextPane();
		lineNumbers = new JTextPane();
		lineNumbers.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textpane);
		scrollPane.setRowHeaderView(lineNumbers);

		Container contentPane = getContentPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// NoWrap JTextPane Setting!!!!!!!
		textpane.setEditorKit(new WrapEditorKit());

		// Display line number on the left side of JTextPane!!!!!!!
		JTextPane lineNumbers = new JTextPane();
		lineNumbers.setEditable(false);
		lineNumbers.setBackground(new Color(243, 243, 243));

		scrollPane.setRowHeaderView(lineNumbers);

		textpane.getDocument().addDocumentListener(new DocumentListener() {
			public String getText() {
				int caretPosition = textpane.getDocument().getLength();
				Element root = textpane.getDocument().getDefaultRootElement();
				String text = "1\n";
				for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
					text += i + "\n";
				}
				return text;
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				lineNumbers.setText(getText());
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				lineNumbers.setText(getText());
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				lineNumbers.setText(getText());
			}
		});

		textpane.setText("This is a long sentence for testing purposes. This is a method to prevent line breaks when setText() a long sentence in JTextPane. We decided to call it No Wrap JTextPane.\n\n이것은 테스트를 위한 긴 문장입니다. JTextPane에서 긴 문장을 setText()했을 때, 개행하지 않도록 처리하는 방법입니다. 우리는 이것을 No Wrap JTextPane이라고 부르기로 했습니다.");
	}
}
