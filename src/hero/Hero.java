package hero;

import java.util.Scanner;

public class Hero {
	   Scanner s=new Scanner(System.in);
	   public String name; 
	   private int Hp,Mp,collectionExp,Exp,Level,X,Y;
	   private int attackvalue,MaxExp,MaxHp,MaxMp;
	   public char id;
	   public String skill;
	   Hero()
	   {
		   id='0';
		   Level=1;
		   Hp=300;
		   Mp=100;
		   MaxExp=Level*50;
		   MaxHp=300;
		   MaxMp=100;
		   attackvalue=Level*50;
		   collectionExp=Level*50;
		   X=0;Y=0;
	   }
	   public boolean choseUseSkill(String skill,Hero t,Map a)
	   {
		   if(skill.equals("A"))
		   {
		   if(Mp<50)
		   {
			   System.out.println(this.name+"的魔法值不足，无法释放技能");
			   return false;
		   }
		   else
		   {
			   this.Hp+=50;
			   t.Hp+=50;
			   this.Mp-=30;
			   if(Hp>MaxHp)
				   Hp=MaxHp;
			   if(t.Hp>t.MaxHp)
				   t.Hp=t.MaxHp;
			   System.out.println(this.name+"使用回复技能，Hp恢复中。。。。。\n全体的Hp恢复了");
			   System.out.println(this.name+"剩余生命值为"+this.Hp+"\n"+t.name+"剩余生命值为"+t.Hp);
			   return true;
		   }
		   }
		   else if(skill.equals("B"))
		   {
			   if(Mp<30)
			   {
				   System.out.println(this.name+"的魔法值不足，无法释放技能");
				   return false;
			   }
			   else
			   {
				   System.out.println(this.name+"使用技能");
				   this.attackvalue*=1.2;
				   this.Mp-=30;
				   System.out.println(this.name+"攻击力提升了");
			   }
			   return true;
		   }
		   else if(skill.equals("C"))
		   {
			   if(Mp<50)
			   {
				   System.out.println(this.name+"的魔法值不足，无法释放技能");
				   return false;
			   }
			   else
			   {
				   System.out.println("请选择释放目标：队友（1），自己（2）");
				   String ch=s.next();
				   System.out.println(this.name+"释放回复技能");
				   if(ch=="2")
				   {
					   this.Hp+=(this.MaxHp-this.Hp)*0.8;
					   System.out.println(this.name+"生命值回复中。。。。。\n"+this.name+"生命值回复了");
				   }
				   if(ch=="1")
				   {
					   t.Hp+=(t.MaxHp-t.Hp)*0.8;
					   System.out.println(t.name+"生命值回复中。。。。。\n"+t.name+"生命值回复了");
				   }
				   return true;
			   }
		   }
		   else if(skill.equals("D"))
		   {
			   if(Mp<50)
			   {
				   System.out.println(this.name+"的魔法值不足，无法释放技能");
				   return false;
			   }
			   else
			   {
				   int i,j;
				   Hero q;
				   System.out.println("请选择技能的释放方向");
				   String dec=s.next();
				   if(dec.equals("N"))
				   {
					   for(i=this.X-1;i>=0;i--)
					   {
						   if(a.getHero(i, this.Y)!=null)
						   {
							   if(i!=this.X-1)
							   a.map[i+1][this.Y]='*';
							   q=a.getHero(i, this.Y);
							   q.setHp(q.getHp()-120);
							   System.out.println(q.name+"被击中，生命值损伤120点，剩余生命值为"+q.getHp());
							   if(!q.islive())
							   {
								   this.getExp(q);
								   a.delete(q);
							   }
							   break;
						   }
						   else
						   {
							   if(i==this.X-1)
							   a.map[i][this.Y]='^';
							   else
							   {   
								   a.map[i][this.Y]='^';
								   a.map[i+1][this.Y]='*';
							   }
							   a.display();
							   System.out.println();
						   }
					   }
					   if(i<0)
						   a.map[i+1][this.Y]='*';
				   }
				   else if(dec.equals("W"))
				   {
					   for(i=this.Y-1;i>=0;i--)
					   {
						   if(a.getHero(this.X , i)!=null)
						   {
							   if(i!=this.Y-1)
							   a.map[this.X][i+1]='*';
							   q=a.getHero(this.X , i);
							   q.setHp(q.getHp()-120);
							   System.out.println(q.name+"被击中，生命值损伤120点，剩余生命值为"+q.getHp());
							   if(!q.islive())
							   {
								   this.getExp(q);
								   a.delete(q);
							   }
							   break;
						   }
						   else
						   {
							   if(i==this.Y-1)
							   a.map[this.X][i]='<';
							   else
							   {   
								   a.map[this.X][i]='<';
								   a.map[this.X][i+1]='*';
							   }
							   a.display();
							   System.out.println();
						   }
					   }
					   if(i<0)
						   a.map[this.X][i+1]='*';
				   }
				   else if(dec.equals("E"))
				   {
					   for(i=this.Y+1;i<=constmatrix.N;i++)
					   {
						   if(a.getHero(this.X , i)!=null)
						   {
							   if(i!=this.Y+1)
							   a.map[this.X][i-1]='*';
							   q=a.getHero(this.X , i);
							   q.setHp(q.getHp()-120);
							   System.out.println(q.name+"被击中，生命值损伤120点，剩余生命值为"+q.getHp());
							   if(!q.islive())
							   {
								   this.getExp(q);
								   a.delete(q);
							   }
							   break;
						   }
						   else
						   {
							   if(i==this.Y+1)
							   a.map[this.X][i]='>';
							   else
							   {   
								   a.map[this.X][i]='>';
								   a.map[this.X][i-1]='*';
							   }
							   a.display();
							   System.out.println();
						   }
					   }
					   if(i>constmatrix.N)
						   a.map[this.X][i-1]='*';
				   }
				   else
				   {
					   for(i=this.X+1;i<=constmatrix.M;i++)
					   {
						   if(a.getHero(i, this.Y)!=null)
						   {
							   if(i!=this.X+1)
							   a.map[i-1][this.Y]='*';
							   q=a.getHero(i, this.Y);
							   q.setHp(q.getHp()-120);
							   System.out.println(q.name+"被击中，生命值损伤120点，剩余生命值为"+q.getHp());
							   if(!q.islive())
							   {
								   this.getExp(q);
								   a.delete(q);
							   }
							   break;
						   }
						   else
						   {
							   if(i==this.X+1)
							   a.map[i][this.Y]='V';
							   else
							   {   
								   a.map[i][this.Y]='V';
								   a.map[i-1][this.Y]='*';
							   }
							   a.display();
							   System.out.println();
						   }
					   }
					   if(i>constmatrix.M)
						   a.map[i-1][this.Y]='*';
				   }
				   return true;
			   }
		   }
		   else
			   return false;
	   }
	   public void setHero(int Hp,int attackvalue)
	   {
		   this.Hp=Hp;
		   this.MaxHp=Hp;
		   this.attackvalue=attackvalue;
	   }
	   public void setX(int X)
	   {
		   this.X=X;
	   }
	   public void setY(int Y)
	   {
		   this.Y=Y;
	   }
	   public void setHp(int Hp)
	   {
		   this.Hp=Hp;
	   }
	   public int getX()
	   {
		   return X;
	   }
	   public int getY()
	   {
		   return Y;
	   }
	   public int getHp()
	   {
		   return Hp;
	   }
	   public int getAttack()
	   {
		   return attackvalue;
	   }
	   public int getMp()
	   {
		   return Mp;
	   }
	   public boolean islive()
	   {
		   if(Hp<=0)
			   return false;
		   else
			   return true;
	   }
	   public boolean isUpLevel()
	   {
		   if(Exp>=MaxExp)
		   {
			   Exp=MaxExp-Exp;
			   return true;
		   }
		   else
			   return false;
	   }
	   public void upDate()
	   {
		   MaxExp+=Level*50;
		   attackvalue+=Level*50;
		   collectionExp+=Level*50;
	   }
	   public void getExp(Hero t)
	   {
		    this.Exp+=t.collectionExp;
		    System.out.println(this.name+"获得"+t.collectionExp+"经验值");
		    if(isUpLevel())
		    {
		    	Level++;
		    	System.out.println(this.name+"英雄升级了，等级为"+this.Level);
		    	upDate();
		    }
	   }

}

