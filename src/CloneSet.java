import java.util.ArrayList;
import java.util.List;

//1つのクローンセットクラス
//クローンをフィールドに持つ
public class CloneSet {
	List<Clone> clonelist = new ArrayList<Clone>();
	int groupId=-1;// プロジェクト内クローンならば，そのプロジェクトIDを格納．
	boolean single = true;// プロジェクトIDが単一かどうか
	boolean spec = true; //プロジェクト固有のクローンかどうか
	int setNum; //上から何番目のクローンセットか．一番上は0番目

	public void changeToNotSingle() {
		single = false;
	}

	public void changeToNotSpec(){
		spec=false;
	}

	public int getGroupId(){
		return groupId;
	}

	public boolean isSingle() {
		return single;
	}

	public boolean isSpec(){
		return spec;
	}

	public void setCloneList(List<Clone> clonelist){
		this.clonelist=clonelist;
	}

	public void setSetNum(int setNum){
		this.setNum=setNum;
	}

	public void setGroupId(int groupId){
		this.groupId=groupId;
	}

	public List<Clone> getCloneList(){
		return clonelist;
	}

	public int getSetNum(){
		return setNum;
	}

//singleかどうか検査する
	public void checkSingle(){
		int groupId=getCloneList().get(0).getGroupId();
		for(Clone clone:getCloneList()){
			if(groupId!=clone.getGroupId()){
				changeToNotSingle();
				changeToNotSpec();
				break;
			}
			else{
				groupId=clone.getGroupId();
			}
		}
	}

	public boolean containsTarget(int targetId){
		boolean contain=false;
		for(Clone clone: getCloneList()){
			if(clone.getGroupId()==targetId){
				contain=true;
				break;
			}
		}
		return contain;
	}
}
