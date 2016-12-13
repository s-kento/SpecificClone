import java.util.ArrayList;
import java.util.List;

//プロジェクト間クローンを漏れなく洗い出すクラス
//プロジェクト間クローンセットと，プロジェクト内クローンセットのoverlap値を算出する
public class CloneClassifier {


	public void classifyGroup(List<CloneSet> csets, double threshold) {
		for (CloneSet cset1 : csets) {
			if (!cset1.isSingle()) {// プロジェクト間のクローンならば
				for (Clone clone1 : cset1.getCloneList()) {// 各クローンがcontainしているsingleのクローンセットを走査していく
					for (CloneSet cset2 : csets) {
						if (cset2.isSingle() && (clone1.getGroupId() == cset2.getGroupId())) {
							for (Clone clone2 : cset2.getCloneList()) {
								if ((clone1.getFileId() == clone2.getFileId()) && isContained(clone2, clone1)) {
									if (calcContained(clone2, clone1) >= threshold) {
										cset2.changeToNotSpec();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// containしているか判定する
	public boolean isContained(Clone cl1, Clone cl2) {
		boolean contained = true;
		if (cl1.getEndLine() < cl2.getStartLine() || cl1.getStartLine() > cl2.getEndLine()) {
			contained = false;
		}
		return contained;
	}

	// containedの値を計算する
	public double calcContained(Clone cl1, Clone cl2) {
		double contained;
		int interStart; // 積集合の開始行
		int interEnd; // 積集合の終了行

		if (cl1.getStartLine() <= cl2.getStartLine()) {
			interStart = cl2.getStartLine();
		} else {
			interStart = cl1.getStartLine();
		}
		if (cl1.getEndLine() >= cl2.getEndLine()) {
			interEnd = cl2.getEndLine();
		} else {
			interEnd = cl1.getEndLine();
		}
		contained = (double)(interEnd - interStart + 1) / (double)(cl1.getEndLine() - cl1.getStartLine() + 1);
		return contained;
	}

	//多様度を計算する
	public double calcDiversity(CloneSet cset){
		double dv=0;
		int total=0;
		int current=0;
		int tmp=0;
		int currentId=cset.getCloneList().get(0).getGroupId();
		List<Integer> occurence=new ArrayList<Integer>();
		occurence.add(tmp);
		for(Clone clone: cset.getCloneList()){//グループIDごとの出現回数をoccurenceに格納
			if(clone.getGroupId()==currentId){
				tmp++;
				total++;
				occurence.set(current, tmp);
			}
			else{
				tmp=1;
				total++;
				current++;
				occurence.add(tmp);
				currentId=clone.getGroupId();
			}
		}
		for(int i=0;i<occurence.size();i++){
			dv+=Math.pow((double)occurence.get(i)/(double)total,2);
		}
		return 1-dv;
	}
}
