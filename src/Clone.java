
public class Clone {
	String line;
	int groupId;
	int fileId;
	int startLine;
	int endLine;

	public void setLine(String line){
		this.line=line;
		analyze();
	}

	public void setGroupId(int groupId){
		this.groupId=groupId;
	}

	public void setFileId(int fileId){
		this.fileId=fileId;
	}

	public void setStartLine(int startLine){
		this.startLine=startLine;
	}

	public void setEndLine(int endLine){
		this.endLine=endLine;
	}

	public int getGroupId(){
		return groupId;
	}

	public int getFileId(){
		return fileId;
	}

	public int getStartLine(){
		return startLine;
	}

	public int getEndLine(){
		return endLine;
	}

	public String getLine(){
		return line;
	}

	public void analyze(){//line情報から，groupIdやstartLineを解析する
		String[] split = line.split("\\s+",0);
		String[] id = split[0].split("\\.",0);
		String[] start = split[1].split(",",0);
		String[] end = split[2].split(",",0);
		setGroupId(Integer.parseInt(id[0]));
		setFileId(Integer.parseInt(id[1]));
		setStartLine(Integer.parseInt(start[0]));
		setEndLine(Integer.parseInt(end[0]));
	}
}
