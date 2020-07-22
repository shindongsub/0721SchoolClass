import java.util.ArrayList;
import java.util.Scanner;

import Util.Util;


public class BoardApp {
	ArrayList<Article> articles = new ArrayList<Article>();
	void start() {
		
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		int id = 4;
		Article article1 = new Article(1, "�׽�Ʈ����1", "�׽�Ʈ����1", "�׽�Ʈ�ۼ���1",
				Util.getCurrentDate(), 6);
		Article article2 = new Article(2, "�׽�Ʈ����2", "�׽�Ʈ����2", "�׽�Ʈ�ۼ���2",
				Util.getCurrentDate(), 4);
		Article article3 = new Article(3, "�׽�Ʈ����3", "�׽�Ʈ����3", "�׽�Ʈ�ۼ���3",
				Util.getCurrentDate(), 3);
		articles.add(article1);
		articles.add(article2);
		articles.add(article3); 
		print_articles(articles);
		
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
				System.out.println("����Ÿ �˻� : search");
				System.out.println("����Ÿ �� : detail");
				
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
				print_articles(articles);
				
				
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
			if (cmd.equals("search")) {
				System.out.println("�˻� �׸��� �������ּ���(1. ����, 2. ����, 3. �ۼ���) : ");
				int searchFlag = Integer.parseInt(sc.nextLine());
				System.out.println("�˻�� �Է����ּ��� : ");
				String Key = sc.nextLine();
				ArrayList<Article> searchArticle = new ArrayList<Article>();
				if (searchFlag == 1) {
					for (int i = 0; i < articles.size(); i++) {
						if (articles.get(i).title.contains(Key)) {
							searchArticle.add(articles.get(i));
						}
					}
				}
				else if (searchFlag == 2) {
					for (int i = 0; i < articles.size(); i++) {
						if (articles.get(i).body.contains(Key)) {
							searchArticle.add(articles.get(i));
						}
					}
				}
				else if (searchFlag == 3) {
					for (int i = 0; i < articles.size(); i++) {
						if (articles.get(i).write.contains(Key)) {
							searchArticle.add(articles.get(i));
						}
					}
				}else{
					System.out.println("�˻������� �����ϴ�.");
				}
				print_articles(searchArticle);
				
			}
			if (cmd.equals("detail")){
				System.out.print("�Խù� ��ȣ�� ������ �ּ��� : ");
				int choice = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(choice);
				if (article == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					article.hit++;
					print_article(article);
				} 
			}
		}
	}
	public void print_article(Article article){
		System.out.println("========== �Խù� ��� ============");
		System.out.println("------ " + article.id
				+ " ��°�Խù�------");
		System.out.println("���� : " + article.title);
		System.out.println("���� : " + article.body);
		System.out.println("�ۼ��� : " + article.write);
		System.out.println("��ȸ�� : " + article.hit);
	}
	public void print_articles(ArrayList<Article> articles){
		System.out.println("========== �Խù� ��� ============");
		for (int i = 0; i < articles.size(); i++) {
			System.out.println("------ " + articles.get(i).id
					+ " ��°�Խù�------");
			System.out.println("���� : " + articles.get(i).title);
			System.out.println("���� : " + articles.get(i).body);
			System.out.println("�ۼ��� : " + articles.get(i).write);
			System.out.println("��ȸ�� : " + articles.get(i).hit);
			
			String str = articles.get(i).regDate;
			String[] array = str.split(" ");
			System.out.println("�ۼ��� : " + array[0]);
		}
	}
	
	public Article get_article_by_id(int id) {
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
