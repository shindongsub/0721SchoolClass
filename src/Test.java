import java.util.ArrayList;
import java.util.Scanner;

import Util.Util;

public class Test {
	static ArrayList<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		int id = 4;
		Article article1 = new Article(1, "�׽�Ʈ����1", "�׽�Ʈ����1", "�׽�Ʈ�ۼ���1",
				Util.getCurrentDate());
		Article article2 = new Article(2, "�׽�Ʈ����2", "�׽�Ʈ����2", "�׽�Ʈ�ۼ���2",
				Util.getCurrentDate());
		Article article3 = new Article(3, "�׽�Ʈ����3", "�׽�Ʈ����3", "�׽�Ʈ�ۼ���3",
				Util.getCurrentDate());
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		while (true) {
			System.out.print("��ɾ �Է��� �ּ��� : ");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("���α׷��� ����Ǿ����ϴ�.");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("����Ÿ ���� : add");
				System.out.println("����Ÿ �б� : read");
				System.out.println("����Ÿ ���� : update");
				System.out.println("����Ÿ ���� : delete");
			}
			if (cmd.equals("add")) {

				Article article = new Article();
				article.id = id;
				id++;
				System.out.print("������ ������ �Է����ּ��� : ");
				String title = sc.nextLine();
				article.title = title;

				System.out.print("������ ������ �Է����ּ��� : ");
				String body = sc.nextLine();
				article.body = body;
				System.out.print("�ۼ��ڸ� �Է����ּ��� : ");
				String write = sc.nextLine();
				article.write = write;
				articles.add(article);
				System.out.println("����Ÿ�� ����Ǿ����ϴ�.");

				article.regDate = Util.getCurrentDate();
			}
			if (cmd.equals("read")) {
				System.out.println("========== �Խù� ��� ============");
				for (int i = 0; i < articles.size(); i++) {
					System.out.println("------ " + articles.get(i).id
							+ " ��°�Խù�------");
					// System.out.println(articles.get(i).regDate);
					System.out.println("���� : " + articles.get(i).title);
					System.out.println("���� : " + articles.get(i).body);
					System.out.println("�ۼ��� : " + articles.get(i).write);

					String str = articles.get(i).regDate;
					String[] array = str.split(" ");
					System.out.println("�ۼ��� : " + array[0]);

				}
			}
			if (cmd.equals("update")) {
				System.out.print("���° �Խù��� �����Ͻðڽ��ϱ�? : ");
				// int targetInt = Integer.parseInt(sc.nextLine());
				// //Integer.parseInt(); �ᵵ�ǰ� sc.nextLine();�ص��ȴ�.
				int targetInt = sc.nextInt();
				sc.nextLine();
				Article targetAticle = get_article_by_id(targetInt);
				if (targetAticle != null) {
					System.out.print("������ ������ �Է����ּ��� : ");
					String Updatetitle = sc.nextLine();
					targetAticle.title = Updatetitle;

					System.out.print("������ ������ �Է����ּ��� : ");
					String Updatebody = sc.nextLine();
					targetAticle.body = Updatebody;

					System.out.print("������ ������ �Է����ּ��� : ");
					String Updatewrite = sc.nextLine();
					targetAticle.write = Updatewrite;
				} else {
					System.out.println("���� �Խù��Դϴ�.");
				}

				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			}
			if (cmd.equals("delete")) {
				System.out.println("���° �Խù��� �����Ͻðڽ��ϱ�?");
				int targetDel = Integer.parseInt(sc.nextLine());
				Article targetRemove = get_article_by_id(targetDel);
				if (targetRemove != null) {
					articles.remove(targetRemove);
					System.out.println("�Խù��� �����Ǿ����ϴ�.");
				} else {
					System.out.println("���� �Խù��Դϴ�.");
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
