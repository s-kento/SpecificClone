import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CloneReader {
	public List<CloneSet> fileRead(String filePath) {
		List<CloneSet> cset = new ArrayList<CloneSet>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			cset = getCloneSet(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cset;
	}

	public List<CloneSet> getCloneSet(BufferedReader br) throws IOException {
		List<CloneSet> cset = new ArrayList<CloneSet>();
		String line = br.readLine();
		while (!(line.equals("#begin{clone}"))) {// #begin{clone}の行までたどりつく
			line = br.readLine();
		}
		line = br.readLine();// #begin{set}
		do {// #begin{set}～#end{set}の中身をひたすら，clonesetに格納していく
			line = br.readLine();
			CloneSet clone = new CloneSet();
			do {
				clone.clonelist.add(line);
				line = br.readLine();
			} while (!line.equals("#end{set}"));
			cset.add(clone);
			line = br.readLine();// #begin{set}か#end{clone}のどちらか
		} while (!(line.equals("#end{clone}")));
		return cset;
	}
}
