import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<CloneSet> clonesets = new ArrayList<CloneSet>();

	public static void main(String[] args) {
		clonesets = new CloneReader().fileRead(args[0]);
		CloneClassifier ccf = new CloneClassifier();
		ccf.classifyGroup(clonesets);
		for (CloneSet cset : clonesets) {
			//System.out.println(cset.getSetNum()+"番目のクローンセット");
			if(cset.isSpec()){
				//System.out.println("グループ固有です．");
			}
			else{
				System.out.println("グループ間です");

			for (Clone clone : cset.clonelist) {
				System.out.print(clone.getGroupId()+".");
				System.out.print(clone.getFileId()+"\t");
				System.out.print(clone.getStartLine()+"\t");
				System.out.println(clone.getEndLine()+"\t");
			}
			}
		}
	}
}
