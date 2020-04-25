package term;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class OutputTextArea extends JTextArea {

	private int num_rows;
	private int num_cols;

	private final String newline = System.getProperty("line.separator");

//	private String[] buffer;

	private int lineCount = 0;

	public OutputTextArea(Document doc, String text, int rows, int columns) {
		// TODO Auto-generated constructor stub
		super(doc, text, rows, columns);
		num_rows = rows;
		num_cols = columns;

//		buffer = new String[rows];

	}

	public void addLine(String text) throws BadLocationException {

//		System.out.println(text);

		String procString = new String(text);

		if (text.length() >= (num_cols - 1)) {
			procString = new String(text.substring(0, num_cols - 2));
		}

//		System.out.println(procString);

//		buffer[lineCount++] = procString;

		// If we have too many lines, delete the top one
		if (lineCount >= num_rows - 1) {
			int start = this.getLineStartOffset(0);
			int end = this.getLineStartOffset(1);
			this.replaceRange(null, start, end);
			lineCount--;
		}

		this.append(procString + newline);
		lineCount++;

	}

	public void clear() throws BadLocationException {
		int start = this.getLineStartOffset(0);
		int end = this.getLineEndOffset(lineCount);
		this.replaceRange(null, start, end);
		lineCount = 0;
	}

	public void addMultilineString(String string) throws BadLocationException {
		// TODO Auto-generated method stub
		String lines[] = string.split("\\r?\\n");
		for (int i = 0; i < lines.length; i++) {
			this.addLine(lines[i]);
		}

	}

}
