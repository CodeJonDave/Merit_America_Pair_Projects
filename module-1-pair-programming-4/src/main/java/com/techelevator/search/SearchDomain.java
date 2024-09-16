package com.techelevator.search;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Search Domain is a list of files to be read and indexed by the Search Engine.
 * 
 * Folders are NOT recursively searched. 
 */
public class SearchDomain {
	private String folder;
	private List<String> files;
	/**
	 * Create a Search Domain of a folder
	 * 
	 * @param folder
	 * @throws SearchDomainException
	 */
	public SearchDomain(String folder) throws SearchDomainException {
		this.folder = folder;
		this.files = buildDomain();
	}
	
	public String getFolder() {
		return folder;
	}

	public List<String> getFiles() {
		return files;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String file : files) {
			sb.append(file).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Folders are NOT recursively searched.
	 * 
	 * @return
	 * @throws SearchDomainException
	 */
	private List<String> buildDomain() throws SearchDomainException {
		List<String> files = new ArrayList<>();
		Path path = Paths.get(this.folder);
		// Step Three: Complete the buildDomain method
		if (Files.exists(path) && Files.isDirectory(path)){
			try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
				for (Path entry : stream){
					if(Files.isRegularFile(entry)){
						files.add(entry.getFileName().toString());
					}
				}

			} catch (IOException e) {
                throw new SearchDomainException("Error reading directory: "+ this.folder);
            }
        }
		else {
			throw new SearchDomainException("Directory does not exist or is it a directory: " + this.folder);
		}
        return files;
	}
}
