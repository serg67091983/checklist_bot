package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Algorithm.TreeWalker;
import Domain.Node;
import Domain.NodeAttribute;
import Domain.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StorageController implements Controller.Interface.IStorageController {
	public Domain.Root Root = new Root();
	private TreeWalker walker = new TreeWalker();
	

	public StorageController(){
		Root.setName("Post AI Book");
	}

	@Override
	public void save(String filePath){
		if(Root == null)
		{
			System.out.println("Can not save empty storage to "+ filePath);
			return;
		}

		ObjectMapper mapper =  new ObjectMapper();
		mapper.enableDefaultTyping();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		FileWriter writer = null;
		try
		{
			String output = mapper.writeValueAsString(Root);
			
			writer = new FileWriter(filePath);
			writer.write(output);	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try
			{
				if(writer != null){
					writer.flush();
					writer.close();				
				}
					
			}
			catch(IOException e1){
					e1.printStackTrace();
			}			
		}
	}
	
	@Override
	public Root open(String filePath){
		
		ObjectMapper mapper =  new ObjectMapper();

		try {
			File file = new File(filePath);	
			Root root = mapper.readValue(file, Root.class);
			this.Root = root;
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return this.Root;
	}

	@Override
	public void addAttribute(String nodeName, NodeAttribute attribute)
	{
		Node result = walker.search(this.Root, nodeName);
		if(result == null) {
			System.out.println(nodeName + " not found ");
			return;
		}

		for(NodeAttribute a : result.getAttributes()){
			if(a.getName() == attribute.getName()){
				a.setBValue(attribute.getBValue());
				System.out.println(nodeName + " updated attribute "+ attribute.getName() + " value "+ attribute.getBValue());
				return;
			}
		}

		System.out.println(nodeName + " added attribute "+ attribute.getName() + " value "+ attribute.getBValue());
		result.getAttributes().add(attribute);
	}
	
}