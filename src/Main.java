import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<CloneSet> clonesets = new ArrayList<CloneSet>();

	public static void main(String[] args) {
		clonesets = new CloneReader().fileRead(args[0]);
		for (CloneSet cset : clonesets) {
			System.out.println(cset.getSetNum()+"番目のクローンセット");
			if(cset.isSingle())
				System.out.println("同一グループです．");
			else
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
