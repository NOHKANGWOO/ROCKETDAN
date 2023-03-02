package view;

import java.util.Scanner;
import controller.Battle;
import controller.Controller_DAO;
import model.Pokemons;
import model.User_Pokemon;
import model.User_VO;
import model.story;

public class View_User {
	public static void main(String[] args) {

		User_Pokemon up = new User_Pokemon();
		Pokemons[] first_pokemon = up.getPokemonsArray();
		Battle battle = new Battle(first_pokemon);
		Scanner sc = new Scanner(System.in);
		Controller_DAO dao = new Controller_DAO();
		// 유저 가입, 로그인에 따른 포켓몬 생성
		User_Pokemon userPokemon = null;
		// 유저 가입, 로그인에 따른 정보 초기화 차이
		story s = new story();
		User_VO userVO = null;
		// 메인 타이틀
		s.title();
		// 회원가입 , 로그인
		System.out.print("[1]회원가입  [2]로그인 >> ");
		int menu = sc.nextInt();
		System.out.println();
		if (menu == 1) {
			s.opening();
			while (true) {
				int choose;
				System.out.println("==========등록==========");
				System.out.println();
				System.out.print("ID : ");
				String id = sc.next();
				System.out.print("PW : ");
				String pw = sc.next();
				System.out.print("nick : ");
				String nick = sc.next();
				int cnt = dao.join(id, pw, nick);
				if (cnt > 0) {
					System.out.println("등록 성공");
					System.out.println("=======================");
					System.out.println("\n");
					userVO = new User_VO(id, pw, nick);
					choose = s.choose();
					battle.arrinsert(first_pokemon, choose); // 선택한 포켓몬 배열 저장
					break;
				} else {
					System.out.println("등록 실패");
					System.out.println("=======================");
					System.out.println("/n");
					continue;
				}
			}
		}while(true){ 
		if (menu == 2) {
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("PW : ");
			String pw = sc.next();
			boolean res = dao.login(id, pw);
			if (res == true) {
				// call_Pokemon 메소드 사용시 포켓몬 담긴 배열 마지막에 유저의 포켓몬 저장
				userVO = new User_VO(id, pw);
				dao.call_Pokemon(first_pokemon, userVO.getId());
				System.out.println("로그인 성공");
				System.out.println("\n");
				break;
			} else {
				System.out.println("로그인 실패");
				System.out.println("\n");
				continue;
			}

		}
		}
			System.out.print("===================== < 선택해주세요 > =====================");
			System.out.println("\n");
			System.out.println("[1]사냥터 [2]체육관도전 [3]상태창 확인 [4]치료센터 [5]세이브 [6]종료");
			System.out.println("\n");
			System.out.print("입력해주세요 : ");
			int move = sc.nextInt();
			if (move == 1) {
				System.out.println("\n");
				System.out.print("사냥터로 이동합니다.");
				System.out.println("\n");
				s.field();
				// battle.vs();
			} else if (move == 2) {
				while (true) {
					System.out.println();
					System.out.print("[1] 첫번째 체육관 [2] 두번째 체육관");
					System.out.print("어떤 체육관에 도전하시겠습니까? : ");
					int gymchoice = sc.nextInt();
					if (gymchoice == 1) {
						System.out.println("\n");
						s.gym1();
						break;
					} else if (gymchoice == 2) {
						System.out.println("\n");
						s.gym2();
						break;
					} else {
						System.out.println("\n");
						System.out.print("잘못 입력하셨습니다.");
						System.out.println("\n");
					}
				}
			} else if (move == 3) {
				System.out.println("\n");
				s.slowPrint("상태창을 출력합니다.", 10);
				System.out.println("\n");
				// 유저 네임 추가 ?
//				System.out.println("포켓몬 : "+userPokemon.getPokemon_name()+" ");
//				System.out.println("레벨 : "+userPokemon.getLevel()+" ");
//				System.out.println("HP : "+userPokemon.getHp()+" ");
//				System.out.println("EXP : "+userPokemon.getExp()+" ");
			} else if (move == 4) {
				System.out.println("\n");
				System.out.print("치료센터로 이동합니다.");
				// 치료 기능 추가
			} else if (move == 5) {
				System.out.println("\n");
				System.out.print("현재까지 플레이 내역을 저장합니다.");
			} else if (move == 6) {
//				매개값으로 유저 id를 받아 포켓몬 정보 확인하기
				// dao.save(first_pokemon ,userVO.getId()); // 해당 유저의 아이디를 받아 아이디로 찾아 유저 포켓몬 정보
				// 최신화
				System.out.println("\n");
				System.out.print("게임을 종료합니다.");

			} else {
				System.out.println("\n");
				System.out.print("잘못 입력하셨습니다!!");
				System.out.println("\n");
			}

		}
	}
