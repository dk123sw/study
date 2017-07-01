package afloat.com.fileceshi.SwipeRefreshLayout;

/**
 * 用户中心List列表信息
 * 
 * @author Administrator
 * 
 */
public class SoftwareClassificationInfo {
	/** 分类编号 **/
	private int cid;
	/** 分类名字 **/
	private String catname;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public SoftwareClassificationInfo() {
		// TODO Auto-generated constructor stub
	}

	public SoftwareClassificationInfo(int cid, String catname) {
		// TODO Auto-generated constructor stub
		this.cid = cid;
		this.catname = catname;
	}
}
