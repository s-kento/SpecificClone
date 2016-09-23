import java.util.List;

//プロジェクト間クローンを漏れなく洗い出すクラス
//プロジェクト間クローンセットと，プロジェクト内クローンセットのoverlap値を算出する
public class CloneClassifier {
	public void classifyGroup(List<CloneSet> csets){
		for(CloneSet cset:csets){
			if(!cset.isSingle()){//プロジェクト間のクローンならば

			}
		}
	}

	//containしているか判定する
	public boolean isContained(Clone cl1, Clone cl2){
		boolean contained=true;
		if(cl1.getEndLine()<cl2.getStartLine() || cl1.getStartLine()>cl2.getEndLine()){
			contained=false;
		}
		return contained;
	}

	//containの値を計算する
	public double calcContained(Clone cl1, Clone cl2){
		double contained;
		int interStart; //積集合の開始行
		int interEnd; //積集合の終了行

		if(cl1.getStartLine()<=cl2.getStartLine()){
			interStart=cl2.getStartLine();
		}
		else{
			interStart=cl1.getStartLine();
		}
		if(cl1.getEndLine()>=cl2.getEndLine()){
			interEnd=cl2.getEndLine();
		}
		else{
			interEnd=cl1.getEndLine();
		}
		contained=(interEnd-interStart+1)/(cl1.getEndLine()-cl1.getStartLine()+1);
		return contained;
	}
}

