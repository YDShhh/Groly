package hero;

public class Map {
	public char [][]map=new char [constmatrix.M][constmatrix.N];
	public Player []player=new Player[2];
	Map()
	{
		player[0]=new Player();
		player[1]=new Player();
		player[0].hero[0].name="1号";
		player[0].hero[1].name="2号";
		player[1].hero[0].name="3号";
		player[1].hero[1].name="4号";
		player[0].hero[0].id='1';
		player[0].hero[1].id='2';
		player[1].hero[0].id='3';
	    player[1].hero[1].id='4';
	    player[0].hero[0].skill="B";
		player[0].hero[1].skill="A";
		player[1].hero[0].skill="D";
		player[1].hero[1].skill="C";
		player[0].setplayer(0);
		player[1].setplayer(1);
		for(int i=0;i<2;i++)
		{
			player[i].hero[0].setHero(200, 100);
			player[i].hero[1].setHero(350, 80);
		}
		for(int X=0;X<constmatrix.M;X++)
		{
			for(int Y=0;Y<constmatrix.N;Y++)
			{
				map[X][Y]='*';
			}
		}
		map[0][0]='1';
		map[1][0]='2';
		map[constmatrix.M-1][constmatrix.N-1]='4';
		map[constmatrix.M-2][constmatrix.N-1]='3';
		System.out.println("初始化游戏地图~");
	}
	public Hero getHero(int X,int Y)
	{
		for(int i=0;i<2;i++)
		for(int j=0;j<2;j++)
		{
			if(player[i].hero[j].getX()==X&&player[i].hero[j].getY()==Y)
				return player[i].hero[j];
		}
		return null;
	}
	public int  findEnemy(Hero t)
	{
		int flag=0;
		if(t.getX()+1<constmatrix.M)
		{
			if(map[t.getX()+1][t.getY()]!='*')
			{
				flag=1;
				System.out.println("下方发现敌人，是否发起攻击");
			}
		}
		if(t.getX()-1>=0)
		{
			if(map[t.getX()-1][t.getY()]!='*')
			{
				flag=1;
				System.out.println("上方发现敌人，是否发起攻击");
			}
		}
		if(t.getY()+1<constmatrix.N)
		{
			if(map[t.getX()][t.getY()+1]!='*')
			{
				flag=1;
				System.out.println("右方发现敌人，是否发起攻击");
			}
		}
		if(t.getY()-1>=0)
		{
			if(map[t.getX()][t.getY()-1]!='*')
			{
				flag=1;
				System.out.println("左方发现敌人，是否发起攻击");
			}
		}
		if(flag==1)
			return 1;
		else
			return 0;
	}
	
	public void delete(Hero t)
	{
		map[t.getX()][t.getY()]='*';
		display();
		System.out.println(t.name+":啊啊啊！兄弟我先走一步了");
		System.out.println(t.name+"已阵亡");
	}
	public void attack(Hero t,String ch)
	{
		Hero s;
		if(ch.equals("S"))
		{
			s=getHero(t.getX()+1,t.getY());
			s.setHp(s.getHp()-t.getAttack());
		}
		else if(ch.equals("N"))
		{
			s=getHero(t.getX()-1,t.getY());
			s.setHp(s.getHp()-t.getAttack());
		}
		else if(ch.equals("E"))
		{
			s=getHero(t.getX(),t.getY()+1);
			s.setHp(s.getHp()-t.getAttack());
		}
		else
		{
			s=getHero(t.getX(),t.getY()-1);
			s.setHp(s.getHp()-t.getAttack());
		}
		if(s.getHp()<0)
			s.setHp(0);
		System.out.println(t.name+"向"+s.name+"发起猛烈攻击\n"+t.name+">>----------------->"+s.name);
		System.out.println(s.name+"受到攻击，Hp下降"+t.getAttack()+",剩余HP为"+s.getHp());
		if(!s.islive())
		{
			t.getExp(s);
			delete(s);
			display();
		}
	}
	public int move(Hero t,String ch,int item)
	{
		if(ch.equals("N"))
		{
			if(t.getX()-item>=0)
			{
			if(map[t.getX()-item][t.getY()]=='*')
			{
				map[t.getX()-item][t.getY()]=t.id;
				map[t.getX()][t.getY()]='*';
				t.setX(t.getX()-item);
				return 1;
			}
			else
				return 0;
			}
			return 0;
		}
		else if(ch.equals("W"))
		{  
			if(t.getY()-item>=0){
			if(map[t.getX()][t.getY()-item]=='*')
			{
				map[t.getX()][t.getY()-item]=t.id;
				map[t.getX()][t.getY()]='*';
				t.setY(t.getY()-item);
				return 1;
			}
			else
				return 0;
			}
			return 0;
		}
		else if(ch.equals("E"))
		{
			if(t.getY()+item<=constmatrix.N-1){
			if(map[t.getX()][t.getY()+item]=='*')
			{
				map[t.getX()][t.getY()+item]=t.id;
				map[t.getX()][t.getY()]='*';
				t.setY(t.getY()+item);
				return 1;
			}
			else
				return 0;
			}
			return 0;
		}
		else if(ch.equals("S"))
		{
			if(t.getX()+item<=constmatrix.M-1){
			if(map[t.getX()+item][t.getY()]=='*')
			{
				map[t.getX()+item][t.getY()]=t.id;
				map[t.getX()][t.getY()]='*';
				t.setX(t.getX()+item);
				return 1;
			}
			else
				return 0;
			}
			return 0;
		}
		return 0;
	}
	
	public void show()
	{
		char str=9601;
		for(int i=0;i<30;i++)
		System.out.print(str);
		System.out.println();
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
			{
				System.out.println(player[i].hero[j].name+' '+"attackvalue-"+player[i].hero[j].getAttack()+' '+"Hp-"+player[i].hero[j].getHp()+' '+"Mp-"+player[i].hero[j].getMp());
			}
		for(int i=0;i<30;i++)
			System.out.print(str);
		System.out.println();		
	}
	public void display()
	{
		for(int i=0;i<constmatrix.M;i++)
		{
			for(int j=0;j<constmatrix.N;j++)
			{
				System.out.print(map[i][j]);
				System.out.print(' ');
				if(j==constmatrix.N-1)
					System.out.println();
			}
		}
	}
}

