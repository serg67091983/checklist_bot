package Parser;

import Domain.Node;
import Domain.Note;
import Parser.Interface.IParagraphTextParser;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ParagraphTextParser implements IParagraphTextParser {

	@Override
	public ArrayList<Node> parseTextFile(String filePath){
		ArrayList<Node> notes = new ArrayList<>();

		File file = new File(filePath);
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(file));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			return notes;
		}

		String st = null;
		int count = 0;
		Note node = new Note();
		StringBuilder sb = new StringBuilder();
		do{
			try
			{
				st = reader.readLine();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
			if(st != null){
				if(st.trim().length() == 0)
				{
					if(count > 0)
					{
						node.setText(sb.toString());
						node.setName("NOTE_"+ node.hashCode());
						notes.add(node);
						node = new Note();
						sb = new StringBuilder();
						count = 0;
					}
				}
				else {
					sb.append(st);
					count++;
				}
			}
		}
		while(st != null);

		return notes;
	}
}