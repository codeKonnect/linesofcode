package main.java.com.cleartax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class lines_of_code {

	public static void main(String[] args) throws IOException {

		//String fileName = "C:\\git_clones\\pulseMS\\rule_engine\\src\\main\\java\\com\\amadeus\\pulse\\streaming\\rule\\engine";
		String fileName = System.getProperty("user.dir")+"/src/main/resources";
		System.out.println(fileName);
		List<String> codeFiles = new ArrayList<String>();
		if (FileUtils.fileExists(fileName)) {
			if (Files.isRegularFile(Paths.get(fileName)))
				codeFiles.add(fileName);
			else {
				codeFiles.addAll(FileUtils.getFilesFromDir(fileName));
			}
		}else {
			System.out.println("No files found to fetch the data from the path : "+fileName);
		}
		Map<String, Map<String, Long>> resultData = FileUtils.fetchDataAllFiles(codeFiles);

//		System.out.println(resultData);
		
		System.out.println("Total files found to be parsed : "+ resultData.size()+"\n");
		
if(resultData.size()>0) {
		for (Entry<String, Map<String, Long>> entry : resultData.entrySet()) {
			System.out.println(entry.getKey());
			for (Entry<String, Long> inneramp : entry.getValue().entrySet()) {
				System.out.println(inneramp.getKey().toUpperCase() + " : " + inneramp.getValue());
			}
		}
	}else {
		System.out.println("No files found to fetch the data");
	}

	}

}
