import java.util.ArrayList;
import java.util.Scanner;

public class TestHaja {
	public static void main(String[] args) {
		ArrayList<Article> testList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			Article article2 = new Article(2, "����2", "����2" + i,"2020-07-12 11:20:55", 30, null);
			testList.add(article2);
		}

		int currentPageNo = 1;
		int totalCountOfArticle = testList.size();
		int articlesPerPage = 3;

		int lastPage = totalCountOfArticle / articlesPerPage + 1; // ��ü���������� ������

		Scanner sc = new Scanner(System.in);
		while (true) {
			int startIndex = (currentPageNo-1) * articlesPerPage; // Ŀ��Ʈ�� 1�̴�. -1���ָ� 0. �̹Ƿ� ��ƼŬ 3�� ���ϸ� 0.
			int endIndex = startIndex + articlesPerPage; // �����ε����� ó�� 3. �׷��� ������ 3���� ǥ���Ѵ�.
			//
			for (int i = startIndex; i < endIndex; i++) {  // ��) ó�� 0~2 > 3~5 > 6~8 ������ ����¡��.
				System.out.println(("��ȣ : " + testList.get(i).id));
				System.out.println(("���� : " + testList.get(i).title));
				System.out.println(("���� : " + testList.get(i).body));
			}
			int startPage = currentPageNo - 2; // ��ŸƮ�������� Ŀ��Ʈ�� 3�̴ϱ� -2���ָ� ������ 1

			if (startPage <= 0) {  // ��ŸƮ�������� ���� 1�̴ϱ� �ش�ȵ�. ������ ���ָ� �ؿ�����ó��.
				startPage = 1; // ó�� ��ŸƮ������ ��ŸƮ�������� -2 �̹Ƿ� -1�� ǥ��ȴ� �̸� �������� if����
								// �����ش�.

			}
			System.out.print(1 + " ... ");
			for (int i = startPage; i < startPage + 5; i++) {
				if (i == currentPageNo) {
					System.out.print("[" + i + "] ");

				} else {
					System.out.print(i + " ");
				}
			}
			System.out.print("... " + lastPage + "\n");

			System.out.println("1. next  2. prev");
			int cmd = Integer.parseInt(sc.nextLine());
			if (cmd == 1) {
				currentPageNo++;
			} else if (cmd == 2) {
				currentPageNo--;
			} else{
				System.out.println("�߸��� ��ɾ� �Դϴ�.");
				
			}
		}

		// ����Ʈ ��� / 3���� �������Ƿ� ������ ���δ�.

	}

}