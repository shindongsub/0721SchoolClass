import java.util.ArrayList;
import java.util.Scanner;

public class TestHaja {
	public static void main(String[] args) {
		ArrayList<Article> testList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			Article article2 = new Article(2, "제목2", "내용2" + i,"2020-07-12 11:20:55", 30, null);
			testList.add(article2);
		}

		int currentPageNo = 1;
		int totalCountOfArticle = testList.size();
		int articlesPerPage = 3;

		int lastPage = totalCountOfArticle / articlesPerPage + 1; // 전체페이지개수 나누기

		Scanner sc = new Scanner(System.in);
		while (true) {
			int startIndex = (currentPageNo-1) * articlesPerPage; // 커랜트가 1이다. -1해주면 0. 이므로 아티클 3을 곱하면 0.
			int endIndex = startIndex + articlesPerPage; // 엔드인덱스는 처음 3. 그래서 내용을 3개만 표시한다.
			//
			for (int i = startIndex; i < endIndex; i++) {  // 예) 처음 0~2 > 3~5 > 6~8 순으로 페이징됨.
				System.out.println(("번호 : " + testList.get(i).id));
				System.out.println(("제목 : " + testList.get(i).title));
				System.out.println(("내용 : " + testList.get(i).body));
			}
			int startPage = currentPageNo - 2; // 스타트페이지는 커렌트가 3이니까 -2해주면 시작은 1

			if (startPage <= 0) {  // 스타트페이지가 현재 1이니까 해당안됨. 하지만 없애면 밑에내용처럼.
				startPage = 1; // 처음 스타트했을때 스타트페이지가 -2 이므로 -1로 표기된다 이를 막기위해 if문을
								// 돌려준다.

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
				System.out.println("잘못된 명령어 입니다.");
				
			}
		}

		// 리스트 출력 / 3개씩 떨어지므로 패턴이 보인다.

	}

}