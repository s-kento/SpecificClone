import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<CloneSet> clonesets = new ArrayList<CloneSet>();

	public static void main(String[] args) throws IOException {
		clonesets = new CloneReader().fileRead(args[0]);
		CloneClassifier ccf = new CloneClassifier();
		ccf.classifyGroup(clonesets, Double.parseDouble(args[2]));
		ResultFileController rfc = new ResultFileController();
		rfc.fileRead(args[0]);
		rfc.fileWrite(clonesets, args[1],Integer.parseInt(args[3]));
		for (CloneSet cset : clonesets) {
			if (!cset.isSpec()) {
				System.out.println(cset.getSetNum()+"番目のクローンセット");
				System.out.println("グループ間です");
				for (Clone clone : cset.clonelist) {
					System.out.print(clone.getGroupId() + ".");
					System.out.print(clone.getFileId() + "\t");
					System.out.print(clone.getStartLine() + "\t");
					System.out.println(clone.getEndLine() + "\t");
				}
			}
		}
	}
}
