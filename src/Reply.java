public class Reply {

	int id; // ��� ��ȣ
	int parent_id; // �θ�� ��ȣ
	String body; // ����
	String writer; // �ۼ���
	String regDate; // �ۼ���
	
	public Reply(int id, int parent_id, String body, String writer, String regDate) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
	
}