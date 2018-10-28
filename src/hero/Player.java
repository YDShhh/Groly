package hero;

public class Player {
	Hero [] hero=new Hero[2];
	Player() 
	{
		hero[0]=new Hero();
		hero[1]=new Hero();
	}
	public void setplayer(int action)
	{
		if(action==0)
		{
			hero[0].setX(0);
			hero[0].setY(0);
			hero[1].setX(1);
			hero[1].setY(0);
		}
		else if(action==1)
		{
			hero[1].setX(constmatrix.M-1);
			hero[1].setY(constmatrix.N-1);
			hero[0].setX(constmatrix.M-2);
			hero[0].setY(constmatrix.N-1);
		}
		
	}
}
