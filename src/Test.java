import java.util.ArrayList;
import java.util.Scanner;

import Util.Util;

public class Test {
	static ArrayList<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		int id = 4;
		Article article1 = new Article(1, "테스트제목1", "테스트내용1", "테스트작성자1",
				Util.getCurrentDate());
		Article article2 = new Article(2, "테스트제목2", "테스트내용2", "테스트작성자2",
				Util.getCurrentDate());
		Article article3 = new Article(3, "테스트제목3", "테스트내용3", "테스트작성자3",
				Util.getCurrentDate());
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		while (true) {
			System.out.print("명령어를 입력해 주세요 : ");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("데이타 저장 : add");
				System.out.println("데이타 읽기 : read");
				System.out.println("데이타 수정 : update");
				System.out.println("데이타 삭제 : delete");
			}
			if (cmd.equals("add")) {

				Article article = new Article();
				article.id = id;
				id++;
				System.out.print("저장할 제목을 입력해주세요 : ");
				String title = sc.nextLine();
				article.title = title;

				System.out.print("저장할 내용을 입력해주세요 : ");
				String body = sc.nextLine();
				article.body = body;
				System.out.print("작성자를 입력해주세요 : ");
				String write = sc.nextLine();
				article.write = write;
				articles.add(article);
				System.out.println("데이타가 저장되었습니다.");

				article.regDate = Util.getCurrentDate();
			}
			if (cmd.equals("read")) {
				System.out.println("========== 게시물 목록 ============");
				for (int i = 0; i < articles.size(); i++) {
					System.out.println("------ " + articles.get(i).id
							+ " 번째게시물------");
					// System.out.println(articles.get(i).regDate);
					System.out.println("제목 : " + articles.get(i).title);
					System.out.println("내용 : " + articles.get(i).body);
					System.out.println("작성자 : " + articles.get(i).write);

					String str = articles.get(i).regDate;
					String[] array = str.split(" ");
					System.out.println("작성일 : " + array[0]);

				}
			}
			if (cmd.equals("update")) {
				System.out.print("몇번째 게시물을 수정하시겠습니까? : ");
				// int targetInt = Integer.parseInt(sc.nextLine());
				// //Integer.parseInt(); 써도되고 sc.nextLine();해도된다.
				int targetInt = sc.nextInt();
				sc.nextLine();
				Article targetAticle = get_article_by_id(targetInt);
				if (targetAticle != null) {
					System.out.print("수정할 제목을 입력해주세요 : ");
					String Updatetitle = sc.nextLine();
					targetAticle.title = Updatetitle;

					System.out.print("수정할 제목을 입력해주세요 : ");
					String Updatebody = sc.nextLine();
					targetAticle.body = Updatebody;

					System.out.print("수정할 제목을 입력해주세요 : ");
					String Updatewrite = sc.nextLine();
					targetAticle.write = Updatewrite;
				} else {
					System.out.println("없는 게시물입니다.");
				}

				System.out.println("수정이 완료되었습니다.");
			}
			if (cmd.equals("delete")) {
				System.out.println("몇번째 게시물을 삭제하시겠습니까?");
				int targetDel = Integer.parseInt(sc.nextLine());
				Article targetRemove = get_article_by_id(targetDel);
				if (targetRemove != null) {
					articles.remove(targetRemove);
					System.out.println("게시물이 삭제되었습니다.");
				} else {
					System.out.println("없는 게시물입니다.");
				}

			}
		}

	}

	public static Article get_article_by_id(int id) {
		Article article = null;
		for (int i = 0; i < articles.size(); i++) {
			Article target = articles.get(i);
			if (target.id == id) {
				article = target;
				break;

			}

		}
		return article;
	}

}

class Article {
	String regDate;
	int id;
	String title;
	String body;
	String write;

	Article() {

	}

	Article(int id, String title, String body, String write, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.write = write;
		this.regDate = regDate;
	}
}
