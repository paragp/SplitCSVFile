package com.listen.csvfolder;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchOutputCSVFolder {

	public static void watchCSVFolder() throws Exception {
		// TODO Auto-generated method stub
		WatchService watchService = FileSystems.getDefault().newWatchService();

		String outputCSVPath = "D:\\Projects\\Carrating\\Workspace\\SplitCSVFile\\resource\\output";

		Path path = Paths.get(outputCSVPath);

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
			}
			key.reset();
		}
	}

	public static void main(String[] args) {
		try {
			WatchOutputCSVFolder.watchCSVFolder();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
