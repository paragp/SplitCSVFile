package com.csv.split;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class SplitCSVFile {
	
	public static int csvGenerated = 0;
	
	public static void splitFile()
	{
		long linesWritten=0;
		int count=1;
		int linesPerSplit=1000;
		
		String inputCSVPath = "D:\\Projects\\Carrating\\Workspace\\SplitCSVFile\\resource\\input\\5m Sales Records.csv";
		String outputCSVPath = "D:\\Projects\\Carrating\\Workspace\\SplitCSVFile\\resource\\output";
		
		try {
			File inputCSVFile = new File(inputCSVPath);
			InputStream inputCSVStream = new BufferedInputStream(new FileInputStream(inputCSVFile));
			BufferedReader csvReader = new BufferedReader(new java.io.InputStreamReader(inputCSVStream));
			
			String line = csvReader.readLine();
			
			String fileName = inputCSVFile.getName();
			String outputCSVName = outputCSVPath+File.separator+"CSVPart";
			
			while(line!=null)
			{
				File outputCSVFile = new File(outputCSVName+"_"+count+".csv");
				Writer csvWriter = new OutputStreamWriter(new FileOutputStream(outputCSVFile));
				
				while(line!=null && linesWritten < linesPerSplit)
				{
					csvWriter.write(line + System.lineSeparator());
					line = csvReader.readLine();
					linesWritten++;
				}
				
				csvWriter.close();
				linesWritten=0;
				count++;
				csvGenerated++;
								
			}
			csvReader.close();
			inputCSVFile.delete();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitCSVFile.splitFile();
		System.out.println(SplitCSVFile.csvGenerated);

	}

}
