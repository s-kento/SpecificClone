import java.util.List;

//プロジェクト間クローンを漏れなく洗い出すクラス
//プロジェクト間クローンセットと，プロジェクト内クローンセットのok値を算出する
public class CloneClassifier {
	public void classifyGroup(List<CloneSet> csets){
		for(CloneSet cset:csets){
			if(!cset.isSingle()){//プロジェクト間のクローンならば

			}
		}
	}

	//overlapしているか判定する
	public boolean isOverlap(Clone cl1, Clone cl2){
		boolean overlap=true;
		if(cl1.getEndLine()<cl2.getStartLine() || cl1.getStartLine()>cl2.getEndLine()){
			overlap=false;
		}
		return overlap;
	}

	//overlapの値を計算する
	public double calcOverlap(Clone cl1, Clone cl2){
		double overlap;
		int unionStart; //和集合の開始行
		int unionEnd; //和集合の終了行
		int interStart; //積集合の開始行
		int interEnd; //積集合の終了行

		if(cl1.getStartLine()<=cl2.getStartLine()){
			unionStart=cl1.getStartLine();
			interStart=cl2.getStartLine();
		}
		else{
			unionStart=cl2.getStartLine();
			interStart=cl1.getStartLine();
		}
		if(cl1.getEndLine()>=cl2.getEndLine()){
			unionEnd=cl1.getEndLine();
			interEnd=cl2.getEndLine();
		}
		else{
			unionEnd=cl2.getEndLine();
			interEnd=cl1.getEndLine();
		}
		overlap=(interEnd-interStart+1)/(unionEnd-unionStart+1);
		return overlap;
	}
}

