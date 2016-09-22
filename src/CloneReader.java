import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//入力ファイルからクローンセットを抽出するクラス
public class CloneReader {
	public List<CloneSet> fileRead(String filePath) {
		List<CloneSet> csets = new ArrayList<CloneSet>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			csets = getCloneSet(br);
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
		return csets;
	}

//ファイルを一行ずつ読んでいき，クローンセットごとにクローン情報を格納していく
	public List<CloneSet> getCloneSet(BufferedReader br) throws IOException {
		int setNum=0;
		List<CloneSet> csets = new ArrayList<CloneSet>();
		String line = br.readLine();
		while (!(line.equals("#begin{clone}"))) {// #begin{clone}の行までたどりつく
			line = br.readLine();
		}
		line = br.readLine();// #begin{set}
		do {// #begin{set}～#end{set}の中身をひたすら，clonesetに格納していく
			line = br.readLine();
			CloneSet cset = new CloneSet();
			cset.setSetNum(setNum++);
			do {
				Clone clone = new Clone();
				clone.setLine(line);
				cset.getCloneList().add(clone);
				line = br.readLine();
			} while (!line.equals("#end{set}"));
			csets.add(cset);
			line = br.readLine();// #begin{set}か#end{clone}のどちらか
		} while (!(line.equals("#end{clone}")));
		return csets;
	}
}
