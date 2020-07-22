import java.util.ArrayList;
import java.util.Scanner;

import Util.Util;


public class BoardApp {
	ArrayList<Article> articles = new ArrayList<Article>();
	void start() {
		
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		int id = 4;
		Article article1 = new Article(1, "테스트제목1", "테스트내용1", "테스트작성자1",
				Util.getCurrentDate(), 6);
		Article article2 = new Article(2, "테스트제목2", "테스트내용2", "테스트작성자2",
				Util.getCurrentDate(), 4);
		Article article3 = new Article(3, "테스트제목3", "테스트내용3", "테스트작성자3",
				Util.getCurrentDate(), 3);
		articles.add(article1);
		articles.add(article2);
		articles.add(article3); 
		print_articles(articles);
		
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
				System.out.println("데이타 검색 : search");
				System.out.println("데이타 상세 : detail");
				
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
				print_articles(articles);
				
				
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
			if (cmd.equals("search")) {
				System.out.println("검색 항목을 선택해주세요(1. 제목, 2. 내용, 3. 작성자) : ");
				int searchFlag = Integer.parseInt(sc.nextLine());
				System.out.println("검색어를 입력해주세요 : ");
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
					System.out.println("검색내용이 없습니다.");
				}
				print_articles(searchArticle);
				
			}
			if (cmd.equals("detail")){
				System.out.print("게시물 번호를 선택해 주세요 : ");
				int choice = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(choice);
				if (article == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					article.hit++;
					print_article(article);
				} 
			}
		}
	}
	public void print_article(Article article){
		System.out.println("========== 게시물 목록 ============");
		System.out.println("------ " + article.id
				+ " 번째게시물------");
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("작성자 : " + article.write);
		System.out.println("조회수 : " + article.hit);
	}
	public void print_articles(ArrayList<Article> articles){
		System.out.println("========== 게시물 목록 ============");
		for (int i = 0; i < articles.size(); i++) {
			System.out.println("------ " + articles.get(i).id
					+ " 번째게시물------");
			System.out.println("제목 : " + articles.get(i).title);
			System.out.println("내용 : " + articles.get(i).body);
			System.out.println("작성자 : " + articles.get(i).write);
			System.out.println("조회수 : " + articles.get(i).hit);
			
			String str = articles.get(i).regDate;
			String[] array = str.split(" ");
			System.out.println("작성일 : " + array[0]);
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
