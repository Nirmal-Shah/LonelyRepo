package utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class WriteToCSV
{
	public static void writeDataToCSV(ArrayList<HashMap<String, String>> headerKeys, String[] header, String fileName) throws IOException {
		ICsvMapWriter mapWriter = null;
		ICsvMapReader mapReader = null;
		try {
			mapWriter = new CsvMapWriter(new FileWriter(System.getProperty("user.dir") + "/extractedData/" + fileName + ".csv", true), CsvPreference.STANDARD_PREFERENCE);
			mapReader = new CsvMapReader(new FileReader(System.getProperty("user.dir") + "/extractedData/" + fileName + ".csv"), CsvPreference.STANDARD_PREFERENCE);
			String[] headerReader = mapReader.getHeader(true);
			try {
				if (!headerReader.equals(header)) 
				{

				}
			} catch (NullPointerException e) {
				mapWriter.writeHeader(header);
			}
			Iterator<HashMap<String, String>> listIterator = headerKeys.iterator();
			while (listIterator.hasNext()) {
				HashMap<String, String> map = listIterator.next();
				mapWriter.write(map, header);
			}
		} finally {
			if (mapWriter != null) {
				mapWriter.close();
			}
			if (mapReader != null) {
				mapReader.close();
			}
		}
	}

	}

