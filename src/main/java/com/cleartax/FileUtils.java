package main.java.com.cleartax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	static final List<String> supportedFiles = Arrays.asList("JAVA", "JS", "PY");

	static final String TOTAL = "total";
	static final String BLANK = "blank";
	static final String COMMENT = "comment";
	static final String CODE = "code";

	/**
	 * Checks for file existence 
	 */
	public static boolean fileExists(String filePath) {
		Path path = Paths.get(filePath);
		return Files.exists(path);
	}

	/**
	 * Returns a List<String> object made of file name
	 * with absolute paths , in a given directory.
	 */

	public static List<String> getFilesFromDir(String filePath) {
		List<String> subPathList = null;
		Path path = Paths.get(filePath);

		try {
			Stream<Path> subPath = Files.walk(path);

			subPathList = subPath.filter(Files::isRegularFile).map(Object::toString).collect(Collectors.toList());


			subPath.close();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return subPathList;

	}
	
	/**
	 * 
	 * @param files
	 * @return Map of String and Map(with count of comments , blanks etc) =>all files from the directory
	 */

	public static Map<String, Map<String, Long>> fetchDataAllFiles(List<String> files) {
		Map<String, Map<String, Long>> data = new HashMap<String, Map<String, Long>>();
		for (String file : files) {
			if (fileExists(file)) {
				try {
					Map<String, Long> curFileData = fetchData(file);
					//System.out.println(curFileData);
					data.put(file, curFileData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return data;
	}

	/**
	 * 
	 * @param file
	 * @return get the counts of blanks , comments of a single file
	 * @throws IOException
	 */
	
	public static Map<String, Long> fetchData(String file) throws IOException {
		return getCount(file);
	}

	private static Map<String, Long> getCount(String file) throws IOException {
		long blanks = 0, comments = 0, code = 0, totalLines = 0;
		Map<String, Long> counterMap = new HashMap<String, Long>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				totalLines++;
				if (line.isEmpty())
					blanks++;
				else if (line.startsWith("//"))
					comments++;
				else
					code++;
			}
			reader.close();
			counterMap.put(BLANK, blanks);
			counterMap.put(COMMENT, comments);
			counterMap.put(CODE, code);
			counterMap.put(TOTAL, totalLines);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return counterMap;

	}

}
