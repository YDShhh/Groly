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
		System.out.println("��Ϸ��ʼ");
		sc.display();
		while(true)
		{
			flag=0;
			for(i=0;i<2;i++)
			{
				if(!sc.player[i].hero[0].islive()&&!sc.player[i].hero[1].islive())
				{
					System.out.println("Game over!\n"+i+"����һ��ʤ����");
					flag=1;
					break;
				}
			}
			if(flag==1)
				break;
			System.out.println((action+1)+"����ҿ�ʼ����");
			for(i=0;i<2;i++){
				if(sc.player[action].hero[i].islive()){
				System.out.println("���"+sc.player[action].hero[i].name+"Ӣ�۽��в���");
			order=s.next();
			if(order.equals("attack")){
				System.out.println("��ѡ��ʹ�ü��ܣ�1����������ͨ������2��");
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
					System.out.println("����û�е��ˣ��޷�������\nָ����������������");
					i--;
					continue;
				}
				else 
				{
					System.out.println("��ȷ���Ƿ��𹥣�yes������no������");
					isNo=s.next();
					if(isNo.equals(yes))
					{
						System.out.println("������Ҫ�������˵�λ��");
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
					System.out.println("ָ����������������");
					i--;
					continue;
				}
				flag=sc.findEnemy(sc.player[action].hero[i]);
				sc.display();
				if(flag==0)
				{
					System.out.println("����û�з��ֵ���");
				}
				else
				{
					System.out.println("��ȷ���Ƿ��𹥣�yes������no������");
					isNo=s.next();
					if(isNo.equals(yes))
					{
						System.out.println("������Ҫ�������˵�λ��");
						direction=s.next();
						sc.attack(sc.player[action].hero[i],direction);
					}
				}
			}
			else if(order.equals("stay"))
			{
				System.out.println(sc.player[action].hero[i].name+"Ӣ�����ڵȴ�����");
				continue;
			}
			else
			{
				break;
			}
			}
			}
			action=1-action;
			System.out.println("�غϽ�����");
			sc.display();
			sc.show();
		}
	}
}
