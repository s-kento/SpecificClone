import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//CCFinderが出力したファイルを管理する，また本ツールの出力ファイルを生成するクラス
public class ResultFileController {
	String head= new String();
	List<String> csetsInfo = new ArrayList<String>();

	public void fileRead(String filePath){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = br.readLine();
			head+=(line+"\n");
			while (!(line.equals("#begin{clone}"))) {// #begin{clone}の行までたどりつく
				line = br.readLine();
				head+=(line+"\n");
			}
			line = br.readLine();// #begin{set}
			do {// #begin{set}～#end{set}の中身をひたすら，csetsInfoに格納していく
				String cset = new String();
				cset+=(line+"\n");
				do {
					line = br.readLine();
					cset+=(line+"\n");
				} while (!line.equals("#end{set}"));
				csetsInfo.add(cset);
				line = br.readLine();// #begin{set}か#end{clone}のどちらか
			} while (!(line.equals("#end{clone}")));
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
	}

	public void fileWrite(List<CloneSet> csets, String file_name) throws IOException{
		File file = new File(file_name);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		pw.print(head);
		for(CloneSet cset:csets){
			if(cset.isSpec()){
				pw.write(csetsInfo.get(cset.getSetNum()));
			}
		}
		pw.println("#end{clone}");

		pw.close();
	}
}
