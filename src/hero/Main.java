package hero;

import java.util.Scanner;

public class Main {
	public  static void main(String []args)
	{
		int action=0;
		Scanner s=new Scanner(System.in);
		String order;
		String isNo;
		String yes="yes";
		String no="no";
		String direction;
		String choose;
		int i=0,flag=0;
		Map sc=new Map();
		System.out.println("游戏开始");
		sc.display();
		while(true)
		{
			flag=0;
			for(i=0;i<2;i++)
			{
				if(!sc.player[i].hero[0].islive()&&!sc.player[i].hero[1].islive())
				{
					System.out.println("Game over!\n"+i+"号玩家获得胜利！");
					flag=1;
					break;
				}
			}
			if(flag==1)
				break;
			System.out.println((action+1)+"号玩家开始操作");
			for(i=0;i<2;i++){
				if(sc.player[action].hero[i].islive()){
				System.out.println("请对"+sc.player[action].hero[i].name+"英雄进行操作");
			order=s.next();
			if(order.equals("attack")){
				System.out.println("请选择使用技能（1），还是普通攻击（2）");
				choose=s.next();
				if(choose.equals("1"))
				{
					if(!sc.player[action].hero[i].choseUseSkill(sc.player[action].hero[i].skill, sc.player[action].hero[1-i],sc))
					{
						i--;
						continue;
					}
				}
				else
				{
				flag=sc.findEnemy(sc.player[action].hero[i]);
				if(flag==0)
				{
					System.out.println("附近没有敌人，无法攻击！\n指令有误，请重新输入");
					i--;
					continue;
				}
				else 
				{
					System.out.println("请确定是否发起攻（yes攻击，no待机）");
					isNo=s.next();
					if(isNo.equals(yes))
					{
						System.out.println("请输入要攻击敌人的位置");
						direction=s.next();
						sc.attack(sc.player[action].hero[i],direction);
					}		
				}
				}
			}
			else if(order.equals("move")){
				String ch=s.next();
				int item=s.nextInt();
				flag=sc.move(sc.player[action].hero[i],ch, item);
				if(flag==0){
					System.out.println("指令有误，请重新输入");
					i--;
					continue;
				}
				flag=sc.findEnemy(sc.player[action].hero[i]);
				sc.display();
				if(flag==0)
				{
					System.out.println("附近没有发现敌人");
				}
				else
				{
					System.out.println("请确定是否发起攻（yes攻击，no待机）");
					isNo=s.next();
					if(isNo.equals(yes))
					{
						System.out.println("请输入要攻击敌人的位置");
						direction=s.next();
						sc.attack(sc.player[action].hero[i],direction);
					}
				}
			}
			else if(order.equals("stay"))
			{
				System.out.println(sc.player[action].hero[i].name+"英雄正在等待命令");
				continue;
			}
			else
			{
				break;
			}
			}
			}
			action=1-action;
			System.out.println("回合结束！");
			sc.display();
			sc.show();
		}
	}
}
