import java.util.ArrayList;
import java.util.List;

//1つのクローンセットクラス
//クローンをフィールドに持つ
public class CloneSet {
	List<Clone> clonelist = new ArrayList<Clone>();
	List<Integer> groupId = new ArrayList<Integer>();// リストの要素が複数あればプロジェクト間クローンセット．いらない？
	boolean spec = true;// プロジェクト固有のクローンかどうか

	public void changeToNotSpecific() {
		spec = false;
	}

	public boolean getSpec() {
		return spec;
	}

	public void setCloneList(List<Clone> clonelist){
		this.clonelist=clonelist;
	}

	public List<Clone> getCloneList(){
		return clonelist;
	}

	public boolean isSingle(){
		int groupId=getCloneList().get(0).getGroupId();
		for(Clone clone:getCloneList()){
			if(groupId!=clone.getGroupId()){
				changeToNotSpecific();
				break;
			}
			else{
				groupId=clone.getGroupId();
			}
		}
		return getSpec();
	}
}
