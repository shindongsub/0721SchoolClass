
class Article {
	String regDate;
	int id;
	String title;
	String body;
	String write;
	int hit;

	Article() {

	}

	Article(int id, String title, String body, String write, String regDate,
			int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.write = write;
		this.regDate = regDate;
		this.hit = hit;
	}
}
