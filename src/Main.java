import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<CloneSet> cloneset = new ArrayList<CloneSet>();

	public static void main(String[] args) {
		cloneset = new CloneReader().fileRead(args[0]);
		for (CloneSet cset : cloneset) {
			for (String line : cset.clonelist) {
				System.out.println(line);
			}
		}
	}
}
