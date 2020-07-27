import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import Util.Util;

public class BoardApp {
	
	ArrayList<Article> articles = new ArrayList<Article>();
	ArrayList<Reply> replies = new ArrayList<Reply>();
	int lastArticleId = 0; // �Խù� ��ȣ ������
	int lastReplyId = 0; // ��� ��ȣ ������
	
	void start() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		
		make_test_data();
		print_articles(articles);
		
		while (true) {

			System.out.print("��ɾ �Է����ּ��� :");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("���α׷� ����");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("add : �Խù� ����");
				System.out.println("list : �Խù� ��� ��ȸ");
				System.out.println("detail : �Խù� �� ��ȸ");
				System.out.println("update : �Խù� ����");
				System.out.println("delete : �Խù� ����");
				System.out.println("search : �Խù� �˻�");
				System.out.println("sort : �Խù� ���� ��ȸ");
			}

			if (cmd.equals("add")) {

				Article article = new Article();
				article.id = lastArticleId;
				lastArticleId++;

				System.out.println("������ �Է����ּ���");
				String title = sc.nextLine();
				article.title = title;

				article.regDate = Util.getCurrentDate();

//				System.out.println("������ �Է����ּ���");
//				String body = sc.nextLine();
//				bodies.add(body);
//				article.body = body;

				articles.add(article);
				System.out.println("�Խù��� ��ϵǾ����ϴ�.");

			}
			if (cmd.equals("list")) {
				print_articles(articles);
			}
			if (cmd.equals("update")) {
				System.out.println("� �Խù��� �����Ͻðڽ��ϱ�? : ");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);

				if (targetArticle != null) {

					System.out.println("������ ������ �Է����ּ��� : ");
					String updated_title = sc.nextLine();
					targetArticle.title = updated_title;

					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				} else {
					System.out.println("���� �Խù� ��ȣ�Դϴ�.");
				}

			}
			if (cmd.equals("delete")) {
				System.out.println("� �Խù��� �����Ͻðڽ��ϱ�? :");

				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);

				if (targetArticle != null) {
					articles.remove(targetArticle);
					System.out.println("�Խù��� �����Ǿ����ϴ�.");
				} else {
					System.out.println("���� �Խù� ��ȣ�Դϴ�.");
				}
			}
			if (cmd.equals("search")) {
				System.out.println("�˻� �׸��� �������ּ���. : 1. ����, 2. ����");
				int searchFlag = Integer.parseInt(sc.nextLine());
				System.out.println("�˻�� �Է����ּ���");
				String keyword = sc.nextLine();
				ArrayList<Article> searchedArticles = new ArrayList<>();

				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getPropertyByType(searchFlag).contains(keyword)) {
						searchedArticles.add(articles.get(i));
					}
				}

				print_articles(searchedArticles);
			}
			if (cmd.equals("detail")) {
				System.out.println("�Խù� ��ȣ�� �Է����ּ���.");
				int articleId = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(articleId);

				if (article == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					
					article.hit++;
					print_article(article);
					ArrayList<Reply> replies = get_replies_by_parent_id(articleId);
					print_replies(replies);
					
					while(true) {
						System.out.println("1. ���  2. ���ƿ�  3. ����  4. ����  5. �ڷΰ���");
						int detailCmd = Integer.parseInt(sc.nextLine());
						
						if(detailCmd == 1) {
							
							int replyId = lastReplyId;
							lastReplyId++;
							
							System.out.println("��� ������ �Է����ּ���.");
							String replyBody = sc.nextLine();
							String writer = "�͸�";
							String regDate = Util.getCurrentDate();
							
							Reply new_reply =  new Reply(replyId, articleId, replyBody, writer, regDate);
							this.replies.add(new_reply);
							System.out.println("����� ���������� ��ϵǾ����ϴ�.");
							
							print_article(article);
							ArrayList<Reply> replies2 = get_replies_by_parent_id(articleId);
							print_replies(replies2);
							
						} else if(detailCmd == 2) {
							System.out.println("1. ���ƿ�  2. �Ⱦ��");
							int likeOrHate = Integer.parseInt(sc.nextLine());
							article.set_likes_and_hates("chacha1", likeOrHate);
							print_article(article);
							
						} else if(detailCmd == 5) {
							break;
						}
					}
					
				}
			} else if(cmd.equals("sort")) {
				System.out.println("���� ����� �������ּ��� : 1. ��������,  2. ��������");
				int flag = Integer.parseInt(sc.nextLine());
				sortArticles(articles, flag);
				print_articles(articles);
			}
		}
	}
	
	public ArrayList<Reply> get_replies_by_parent_id(int parent_id) {
		
		ArrayList<Reply> result = new ArrayList<Reply>();
		
		for(int i = 0; i < this.replies.size(); i++) {
			if(this.replies.get(i).parent_id == parent_id) {
				result.add(this.replies.get(i));
			}
		}
		
		return result;
		
	}
	
	public void print_replies(ArrayList<Reply> replies) {
		System.out.println("======== ��� ========");
		for(int i = 0; i < replies.size(); i++) {
			System.out.println("����   : " + replies.get(i).body);
			System.out.println("�ۼ��� : " + replies.get(i).writer);
			System.out.println("����� : " + replies.get(i).regDate);
		}
	}
	
	public void print_article(Article article) {
		System.out.println("======== �Խù� �� ========");
		System.out.println("��ȣ     : " + article.id);
		System.out.println("����     : " + article.title);
		System.out.println("����     : " + article.body);
		System.out.println("��ȸ��   : " + article.hit);
		System.out.println("��ϳ�¥ : " + article.regDate);
		
		HashMap<String, Integer> resultMap = article.get_likes_and_hates();		
		System.out.println("���ƿ�   : " + resultMap.get("like"));
		System.out.println("�Ⱦ��   : " + resultMap.get("hate"));
	}
	
	public void print_articles(ArrayList<Article> articles) {
		System.out.println("======== �Խù� ��� ========");
		for (int i = 0; i < articles.size(); i++) {
			System.out.println("��ȣ   : " + articles.get(i).id);
			System.out.println("����   : " + articles.get(i).title);

			String str = articles.get(i).regDate;
			String[] arr = str.split(" ");
			System.out.println("�ۼ��� : " + arr[0]);
			System.out.println("��ȸ�� : " + articles.get(i).hit);
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
	
	public void sortArticles(ArrayList<Article> articles, int flag) {
		
		Collections.sort(articles, new Comparator<Article>() {
		
		    @Override
		    public int compare(Article a1, Article a2) {
		    	int c1 = a1.get_likes_and_hates().get("like");
				int c2 = a2.get_likes_and_hates().get("like");
				
				if(flag == 2) {
					int tmp = c1;
					c1 = c2;
					c2 = tmp;
				}
				
		        if( c1 > c2) {
		        	return 1;	
		        } else {
		        	return -1;
		        }
		    }
		});
	}
	
	public void make_test_data() {
		
		HashMap<String, Integer> likes1 = new HashMap<>();
		likes1.put("chacha1", 1);
		likes1.put("chacha2", 2);
		likes1.put("chacha3", 1);
		
		HashMap<String, Integer> likes2 = new HashMap<>();
		likes2.put("chacha1", 2);
		likes2.put("chacha2", 2);
		likes2.put("chacha3", 2);
		
		HashMap<String, Integer> likes3 = new HashMap<>();
		likes3.put("chacha1", 1);
		likes3.put("chacha2", 1);
		likes3.put("chacha3", 1);
		
		Article article1 = new Article();
		article1.id = 1;
		article1.title = "�׽�Ʈ ������ ����1";
		article1.body = "�׽�Ʈ ������ ����1";
		article1.regDate = Util.getCurrentDate();
		article1.hit = 20;
		article1.likesAndHates = likes1;

		Article article2 = new Article(2, "����2", "����2", Util.getCurrentDate(), 30, likes2);
		Article article3 = new Article(3, "����3", "����3", Util.getCurrentDate(), 5, likes3);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		
		lastArticleId = 4;
		
		Reply r1 = new Reply(1, 1, "���1", "�ۼ���1", Util.getCurrentDate());
		Reply r2 = new Reply(2, 1, "���2", "�ۼ���2", Util.getCurrentDate());
		Reply r3 = new Reply(3, 2, "���3", "�ۼ���3", Util.getCurrentDate());
		
		lastReplyId = 4;
		
		replies.add(r1);
		replies.add(r2);
		replies.add(r3);
	}
}