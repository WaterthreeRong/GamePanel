/*
 * 功能：用来表示游戏项目的画板
 * 作者：ZR
 * 时间：2018.9.29
 * 为配角加上动态效果
 * 1.将配角所有图片内容读取保存到程序内存当中
 * 2.实现Runnable接口，添加接口中的方法
 */
package com.zr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//编写自定义画板类添加绘画方法
public class GamePanel extends JPanel implements Runnable,KeyListener{
	//定义变量用来表示当前场景所在的编号
	//规定李家村为1号，李家村市场为2号
	int mapID;
	//定义图片的相关内容
	Image ljcImage;//表示李家村图片内容
	int ljcX;//李家村X坐标
	int ljcY;//李家村Y坐标
	Image[] lxyUPImages;
	Image[] lxyDownImages;
	Image[] lxyLeftImages;
	Image[] lxyRightImages;
	Image lxyImage;//用来表示李逍遥当前图片内容
	int lxyX;
	int lxyY;
	int lxyIndex;
	int lxyDir;
	Image[] awsImages;//定义一个图片数组用来存放所有阿旺婶图片
	int awsIndex;//定义表示阿旺婶当前图片对应下标
	int awsX;
	int awsY;
	Image[] azImages;
	int azIndex;
	int azX;
	int azY;
	Image[] mjImages;
	int mjIndex;
	int mjX;
	int mjY;
	Image[] wcsImages;
	int wcsIndex;
	int wcsX;
	int wcsY;
	Image[] xhImages;
	int xhIndex;
	int xhX;
	int xhY;
	Image[] xjImages;
	int xjIndex;
	int xjX;
	int xjY;
	Image[] xxjImages;
	int xxjIndex;
	int xxjX;
	int xxjY;
	//创建聊天的背景图和聊天的内容
	Image ltImage;
	int ltX;
	int ltY;
	String[] ltawsString;
	int ltawsIndex;
	boolean isltawsShow;
	String[] ltazString;
	int ltazIndex;
	boolean isltazShow;
	String[] ltwcsString;
	int ltwcsIndex;
	boolean isltwcsShow;
	String[] ltxhString;
	int ltxhIndex;
	boolean isltxhShow;
	//定义变量用来存储李家村数据地图
	BufferedImage ljcDataMap;//BufferedImage作为Image的子类才可以获取图上的颜色
	//定义变量存储传送门
	Image csmImage;
	int csmX;
	int csmY;
	int csmw;
	int csmh;
	//定义变量存储李家村市场
	Image[] ljcscImages;
	int ljcscX;
	int ljcscY;
	int ljcscIndex;
	//定义变量用来存储李家村市场数据地图
	BufferedImage ljcscDataMap;
	//定义李家村市场人物图片
	//定义李家村市场阿紫人物变量
	Image aziImage;
	int aziX;
	int aziY;
	int aziw;
	int azih;
	//定义李家村市场阿兰人物变量
	Image alImage;
	int alX;
	int alY;
	int alw;
	int alh;
	//定义李家村市场小二人物变量
	Image xeImage;
	int xeX;
	int xeY;
	int xew;
	int xeh;
	//定义主角与李家村市场人物的聊天内容
	String[] ltalString;
	int ltalIndex;
	boolean isltalShow;
	String[] ltaziString;
	int ltaziIndex;
	boolean isltaziShow;
	String[] ltxeString;
	int ltxeIndex;
	boolean isltxeShow;
	//构造方法
	public GamePanel()
	{
		//初始化mapID
		mapID=1;
		//绝对路径：以C盘符或者根目录（/）开始的路径
		//相对路径：Java工程中的相对路径从工程名称后第一个斜杠后面开始写
		//李家村
		try {
			ljcImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCun/0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ljcX=-200;
		ljcY=-200;
		//初始化聊天的背景图和聊天内容
		try {
			ltImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiaoTian/0.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//this.getWidth()这个方法需要在窗口显示了之后才可以调用获取到相应的值
		/*ltX=(this.getWidth()-ltImages[1].getWidth(null))/2;
		ltY=this.getHeight()-ltImages[1].getHeight(null);*/
		//所以上面的语句写在构造函数中时是在窗口显示化之前获取的值，此时无法通过this.
		//来获取到值，所以应该写在paint()方法中
		//初始化李家村配角的聊天内容
		isltawsShow=false;
		ltawsString=new String[] {
				"李逍遥：阿旺婶，早啊，好久不见！",
				"阿旺婶：是逍遥呀，回来啦！",
				"李逍遥：是呀，今天刚回来。",
				"李逍遥：是呀，今天刚回来。",
				"李逍遥：您最近怎么样呀？一切都还好吧？",
				"阿旺婶：一切都挺好的嘞！",
				"李逍遥：那你先忙，我先走啦！改天再去您家拜访您！",
				"阿旺婶：好呀！一定来呀！",
				"李逍遥：好的！"};
		ltawsIndex=0;
		isltazShow=false;
		ltazString=new String[] {
				"阿朱：逍遥你回来啦？",
				"李逍遥：是呀！"};
		ltazIndex=0;
		isltwcsShow=false;
		ltwcsString=new String[] {
				"旺财嫂：哟！是逍遥呀！",
				"李逍遥：是我，旺财嫂。我是逍遥。",
				"旺财嫂：刚回来吧？！吃过了吗？没吃的话上我家来吃！",
				"李逍遥：不用麻烦了您了嫂子，我吃过了，谢谢您的好意！",
				"李逍遥：我改天再去您家拜访您。",
				"旺财嫂：好！好！那你路上当心！",
				"李逍遥：诶，好嘞！"};
		ltwcsIndex=0;
		isltxhShow=false;
		ltxhString=new String[] {
				"小女孩：逍遥哥哥！",
				"跳绳小孩：逍遥哥哥你回来啦！",
				"光头小孩：逍遥哥哥你什么时候带我们去抓蟋蟀呀？",
				"小女孩：逍遥哥哥明明先答应了要带我去放风筝的！",
				"小女孩：逍遥哥哥你什么时候带我去呀？",
				"李逍遥：好好好！都别吵了啦！我答应你们。",
				"李逍遥：改天我们一起放风筝抓蟋蟀好不好呀？",
				"李逍遥：不过逍遥哥哥今天不能陪你们玩了。",
				"李逍遥：逍遥哥哥还有事儿要先走一步",
				"李逍遥：改天一有空我就去找你们！",
				"小女孩：好呀好呀！那逍遥哥哥再见！",
				"光头小孩：逍遥哥哥再见！",
				"跳绳小孩：再见逍遥哥哥！",
				"李逍遥：再见啦！"};
		ltxhIndex=0;
		//
		lxyUPImages=new Image[8];
		lxyDownImages=new Image[8];
		lxyLeftImages=new Image[8];
		lxyRightImages=new Image[8];
		for(int i=0;i<lxyUPImages.length;i++){
			try {
				lxyUPImages[i]=ImageIO.read(new 
				File("Legend_of_Sword_and_Fairy/LiXiaoYao_Up/"+i+".png"));
				lxyDownImages[i]=ImageIO.read(new 
				File("Legend_of_Sword_and_Fairy/LiXiaoYao_Down/"+i+".png"));
				lxyLeftImages[i]=ImageIO.read(new 
				File("Legend_of_Sword_and_Fairy/LiXiaoYao_Left/"+i+".png"));
				lxyRightImages[i]=ImageIO.read(new 
				File("Legend_of_Sword_and_Fairy/LiXiaoYao_Right/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lxyImage=lxyDownImages[0];
		lxyIndex=0;
		lxyX=500;
		lxyY=650;
		lxyDir=KeyEvent.VK_DOWN;
		awsImages=new Image[17];
		//将第i张图片读取保存到数组对应的第i个位置上
		//循环次数已知可以使用for循环，未知可以使用while循环
		for(int i=0;i<awsImages.length;i++){
			//循环存放17张图片
			try {
				awsImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/AWangShen/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		awsIndex=0;
		awsX=680;
		awsY=580;
		azImages=new Image[6];
		for(int i=0;i<azImages.length;i++){
			try {
				azImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/AZhu/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		azIndex=0;
		azX=510;
		azY=450;
		mjImages=new Image[6];
		for(int i=0;i<mjImages.length;i++){
			try {
				mjImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/MuJi/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mjIndex=0;
		mjX=430;
		mjY=500;
		wcsImages=new Image[14];
		for(int i=0;i<wcsImages.length;i++){
			try {
				wcsImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/WangCaiSao/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		wcsIndex=0;
		wcsX=780;
		wcsY=580;
		xhImages=new Image[4];
		for(int i=0;i<xhImages.length;i++){
			try {
				xhImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/XiaoHai/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		xhIndex=0;
		xhX=950;
		xhY=700;
		xjImages=new Image[2];
		for(int i=0;i<xjImages.length;i++){
			try {
				xjImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/XiaoJi/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		xjIndex=0;
		xjX=480;
		xjY=550;
		xxjImages=new Image[2];
		for(int i=0;i<xxjImages.length;i++){
			try {
				xxjImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/XiaoXiaoJi/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		xxjIndex=0;
		xxjX=500;
		xxjY=520;
		try {
			ljcDataMap=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCun/RedMap.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			csmImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy/ChuangSongMen/4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		csmX=155;
		csmY=700;
		csmw=60;
		csmh=100;
		//李家村市场
		//初始化聊天的背景图和聊天内容
		ltxhIndex=0;
		isltalShow=false;
		ltalString=new String[] {
				"阿兰：是逍遥呀！你好久没来我这啦！",
				"李逍遥：是呀，最近生意怎么样？",
				"阿兰：还不错！",
				"阿兰：最近刚酿了一种新酒，要不要带瓶回去尝尝？",
				"李逍遥：好啊，那我一会儿过来取！",
				"阿兰：好，那一会儿见！"
		};
		ltalIndex=0;
		isltxeShow=false;
		ltxeString=new String[]{
			"小二：客官您是打尖儿还是住店啊？",
			"李逍遥：不不，我只是随便逛逛。"
		};
		ltxeIndex=0;
		isltaziShow=false;
		ltaziString=new String[] {
			"阿紫：客官要坐船吗？",
			"李逍遥：不用了，我只是过来看看。"
		};
		//初始化李家村市场
		ljcscImages=new Image[3];
		for(int i=0;i<ljcscImages.length;i++){
			try {
				ljcscImages[i]=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCunShiChang/"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ljcscIndex=0;
		ljcscX=-200;
		ljcscY=-200;
		try {
			ljcscDataMap=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCunShiChang/RedMap.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//初始化李家村市场的人物
		//初始化阿紫
		try {
			aziImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy\\LiJiaCunShiChang\\azi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aziX=1500;
		aziY=275;
		aziw=90;
		azih=125;
		//初始化阿兰
		try {
			alImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy\\LiJiaCunShiChang\\alan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alX=200;
		alY=350;
		alw=50;
		alh=118;
		//初始化小二
		try {
			xeImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy\\LiJiaCunShiChang\\xiaoer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xeX=640;
		xeY=200;
		xew=35;
		xeh=110;
	}
	//初始化图片素材内容
    public void paint(Graphics g)
   {
    	super.paint(g);
    	if(mapID==1){
    		//如果李逍遥在李家村场景上，则需要绘画李家村的场景
    		//准备将图片素材复制到Java工程下
        	//绘画图片素材相关内容
        	ljcX=(this.getWidth()-lxyImage.getWidth(null))/2-lxyX;
        	ljcY=(this.getHeight()-lxyImage.getHeight(null))/2-lxyY;
        	//判断处理边界问题
        	//上下为一组逻辑，左右为一组逻辑，上下中的一个和左右中的一个可能同时出现
        	if(ljcY>=0) ljcY=0;
        	else if(ljcY<=this.getHeight()-ljcImage.getHeight(null)) 
        	ljcY=this.getHeight()-ljcImage.getHeight(null);
        	if(ljcX>=0) ljcX=0;
        	else if(ljcX<=this.getWidth()-ljcImage.getWidth(null)) 
        	ljcX=this.getWidth()-ljcImage.getWidth(null);
        	g.drawImage(ljcImage,ljcX,ljcY,this);
        	g.drawImage(lxyImage,lxyX+ljcX,lxyY+ljcY,this);
        	g.drawImage(awsImages[awsIndex],awsX+ljcX,awsY+ljcY,this);
        	g.drawImage(azImages[azIndex],azX+ljcX,azY+ljcY,this);
        	g.drawImage(mjImages[mjIndex],mjX+ljcX,mjY+ljcY,this);
        	g.drawImage(wcsImages[wcsIndex],wcsX+ljcX,wcsY+ljcY,this);
        	g.drawImage(xhImages[xhIndex],xhX+ljcX,xhY+ljcY,this);
        	g.drawImage(xjImages[xjIndex],xjX+ljcX,xjY+ljcY,this);
        	g.drawImage(xxjImages[xxjIndex],xxjX+ljcX,xxjY+ljcY,this);
        	g.drawImage(csmImage, csmX+ljcX, csmY+ljcY,csmw,csmh, this);
        	//聊天效果
        	//效果1：当程序运行之后，在窗口出现聊天背景和聊天内容
        	//聊天内容画法：g.drawString("聊天内容",x,y);
        	//效果2：通过键盘上的空格按键控制效果1的出现和消失
        	//效果3：只有当主角走到配角面前时才会实现效果2
        	//效果4：实现聊天内容的切换（多句话）
        	//画出聊天背景和聊天效果
        	if(isltawsShow)
        	{
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltawsString[ltawsIndex],ltX+30,ltY+50);
        	}
        	else if(isltazShow){
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltazString[ltazIndex],ltX+30,ltY+50);
        	}
        	else if(isltwcsShow) {
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltwcsString[ltwcsIndex],ltX+30,ltY+50);
        	}
        	else if(isltxhShow) {
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltxhString[ltxhIndex],ltX+30,ltY+50);
        	}
    	}
    	else if(mapID==2){
    		ljcscX=(this.getWidth()-lxyImage.getWidth(null))/2-lxyX;
        	ljcscY=(this.getHeight()-lxyImage.getHeight(null))/2-lxyY;
        	//判断处理李家村市场的边界问题
        	if(ljcscY>=0) ljcscY=0;
        	else if(ljcscY<=this.getHeight()-ljcscImages[ljcscIndex].getHeight(null)) 
        		ljcscY=this.getHeight()-ljcscImages[ljcscIndex].getHeight(null);
        	if(ljcscX>=0) ljcscX=0;
        	else if(ljcscX<=this.getWidth()-ljcscImages[ljcscIndex].getWidth(null)) 
        		ljcscX=this.getWidth()-ljcscImages[ljcscIndex].getWidth(null);
    		//如果李逍遥在李家村市场场景上，则需要绘画李家村市场的场景
    		g.drawImage(ljcscImages[ljcscIndex],ljcscX,ljcscY,this);
    		g.drawImage(lxyImage,lxyX+ljcscX,lxyY+ljcscY,this);
    		g.drawImage(csmImage,csmX+ljcscX,csmY+ljcscY,csmw,csmh,this);
    		g.drawImage(aziImage, aziX+ljcscX, aziY+ljcscY,aziw,azih, this);
    		g.drawImage(alImage, alX+ljcscX, alY+ljcscY,alw,alh,this);
    		g.drawImage(xeImage, xeX+ljcscX, xeY+ljcscY,xew,xeh,this);
    		if(isltalShow)
        	{
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltalString[ltalIndex],ltX+30,ltY+50);
        	}
    		else if(isltxeShow)
    		{
    			ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltxeString[ltxeIndex],ltX+30,ltY+50);
    		}
    		else if(isltaziShow)
    		{
    			ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("微软雅黑",Font.BOLD,18));
        	    g.drawString(ltaziString[ltaziIndex],ltX+30,ltY+50);
    		}
    	}
   }
	@Override
	//Runnable接口中对应的方法
	public void run() {
		// TODO Auto-generated method stub
		//3.完成方法实现配角的动态效果
		while(true){
			if(mapID==1){
				awsIndex++;
				if(awsIndex>=awsImages.length)
					awsIndex=0;
				azIndex++;
				if(azIndex>=azImages.length)
					azIndex=0;
				mjIndex++;
				if(mjIndex>=mjImages.length)
					mjIndex=0;
				wcsIndex++;
				if(wcsIndex>=wcsImages.length)
					wcsIndex=0;
				xhIndex++;
				if(xhIndex>=xhImages.length)
					xhIndex=0;
				xjIndex++;
				if(xjIndex>=xjImages.length)
					xjIndex=0;
				xxjIndex++;
				if(xxjIndex>=xxjImages.length)
					xxjIndex=0;
			}
			else if(mapID==2){
				//李家村市场动态效果
				ljcscIndex++;
				if(ljcscIndex>=ljcscImages.length)
					ljcscIndex=0;
			}
			repaint();
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//KeyListener接口中对应的方法
	@Override
	public void keyPressed(KeyEvent e) {
	
		// TODO Auto-generated method stub
		//
		if(isltawsShow){
			//聊天内容切换加聊天结束
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE){
				ltawsIndex++;
				if(ltawsIndex>=ltawsString.length)
				{
					ltawsIndex=0;
					isltawsShow=false;
				}
				repaint();
			}
		}
		else if(isltazShow){
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE){
				ltazIndex++;
				if(ltazIndex>=ltazString.length)
				{
					ltazIndex=0;
					isltazShow=false;
				}
				repaint();
			}
		}
		else if(isltwcsShow) {
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE) {
				ltwcsIndex++;
				if(ltwcsIndex>=ltwcsString.length) {
					ltwcsIndex=0;
					isltwcsShow=false;
				}
				repaint();
			}
		}
		else if(isltxhShow) {
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE) {
				ltxhIndex++;
				if(ltxhIndex>=ltxhString.length) {
					ltxhIndex=0;
					isltxhShow=false;
				}
				repaint();
			}
		}
		else if(isltalShow) {
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE) {
				ltalIndex++;
				if(ltalIndex>=ltalString.length) {
					ltalIndex=0;
					isltalShow=false;
				}
				repaint();
			}
		}
		else if(isltxeShow) {
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE) {
				ltxeIndex++;
				if(ltxeIndex>=ltxeString.length) {
					ltxeIndex=0;
					isltxeShow=false;
				}
				repaint();
			}
		}
		else if(isltaziShow) {
			int Keycode=e.getKeyCode();
			if(Keycode==KeyEvent.VK_SPACE) {
				ltaziIndex++;
				if(ltaziIndex>=ltaziString.length) {
					ltaziIndex=0;
					isltaziShow=false;
				}
				repaint();
			}
		}
		else{
			//移动加聊天开始
			//参数e为事件源
			//实现主角的运动效果
			//上38 下40 左37右39 w87 s83 a65 d68
			int keycode=e.getKeyCode();
			if(keycode==38)
			{
				int x=lxyX+lxyImage.getWidth(null)/2;
				int y=lxyY+lxyImage.getHeight(null);
				if(mapID==1){
					//判断处理李逍遥的边界
					if(lxyY<=0) lxyY=0;
					lxyY-=5;
					lxyIndex++;
					if(lxyIndex>=lxyUPImages.length) 
						lxyIndex=0;
					lxyImage=lxyUPImages[lxyIndex];
					lxyDir=KeyEvent.VK_UP;
					//判断李逍遥障碍物功能
					//李逍遥向上移动一次后，如果进入障碍物区域，则需要李逍遥回退操作
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了阿旺婶的边界
					int x0=lxyX+lxyImage.getWidth(null)/2;
					int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX;int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了阿朱的边界
					x0=lxyX; y0=lxyY;
					x2=azX+azImages[azIndex].getWidth(null);
					y2=azY;
					x1=azX-25; y1=y2+80;
					if(x0>x1&&x0<x2&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入母鸡以及小鸡们的边界
					x2=mjX+mjImages[mjIndex].getWidth(null)+20; 
					y2=mjY+mjImages[mjIndex].getHeight(null);
					x1=mjX-20; y1=mjY;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了传送门的边界
					x0=lxyX+lxyImage.getWidth(null)/2;
					y0=lxyY;
					x1=csmX-20; y1=csmY+csmh;
					x2=csmX+csmw+20; y2=y1+10;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
				}
				else if(mapID==2){
					if(lxyY<=0) lxyY=0;
					lxyY-=5;
					lxyIndex++;
					if(lxyIndex>=lxyUPImages.length) 
						lxyIndex=0;
					lxyImage=lxyUPImages[lxyIndex];
					lxyDir=KeyEvent.VK_UP;
					//判断李逍遥是否进入障碍物区域
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了阿兰的边界
					int x0=lxyX; int y0=lxyY;
					int x2=alX-10; int y2=alY+alh-10;
					int x1=alX+alw; int y1=y2+5;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了小二的边界
					x2=xeX-50; y2=xeY+xeh-15;
					x1=xeX+xew-10; y1=y2+10;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//判断李逍遥向上走的时候是否进入了传送门的边界
					x0=lxyX+lxyImage.getWidth(null)/2;
					y0=lxyY;
					x1=csmX-20; y1=csmY+csmh;
					x2=csmX+csmw+20; y2=y1+10;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
				}
				repaint();
			}
			else if(keycode==40)
			{
				int x=lxyX+lxyImage.getWidth(null)/2;
				int y=lxyY+lxyImage.getHeight(null);
				if(mapID==1){
					//判断处理李逍遥边界问题
					if(lxyY>=ljcImage.getHeight(null)-lxyImage.getHeight(null))
						lxyY=ljcImage.getHeight(null)-lxyImage.getHeight(null);
					lxyY+=5;
					lxyIndex++;
					if(lxyIndex>=lxyDownImages.length)
						lxyIndex=0;
					lxyImage=lxyDownImages[lxyIndex];
					lxyDir=KeyEvent.VK_DOWN;
					//判断李逍遥是否进入了障碍物区域
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyY-=5;
					}
					//判断李逍遥向下走的时候是否进入了阿旺婶边界区域
					int x0=lxyX+lxyImage.getWidth(null)/2;
					int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX;int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
					//判断李逍遥向下走的时候是否进入了旺财嫂的边界区域
					x1=wcsX; y1=wcsY;
					x2=wcsX+wcsImages[wcsIndex].getWidth(null);
					y2=wcsY+wcsImages[wcsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
					//判断李逍遥向下走的时候是否进入了小孩边界区域
					x1=xhX; y1=xhY;
					x2=xhX+xhImages[xhIndex].getWidth(null);
					y2=xhY+xhImages[xhIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
				}
				else if(mapID==2){
					if(lxyY>=ljcscImages[ljcscIndex].getHeight(null)-lxyImage.getHeight(null))
						lxyY=ljcscImages[ljcscIndex].getHeight(null)-lxyImage.getHeight(null);
					lxyY+=5;
					lxyIndex++;
					if(lxyIndex>=lxyDownImages.length)
						lxyIndex=0;
					lxyImage=lxyDownImages[lxyIndex];
					lxyDir=KeyEvent.VK_DOWN;
					//判断李逍遥是否进入障碍物区域
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyY-=5;
					}
					//判断李逍遥向下走的时候是否进入了传送门的边界
					int x0=lxyX+lxyImage.getWidth(null)/2;
					int y0=lxyY+lxyImage.getHeight(null);
					int x1=csmX-20; int y1=csmY-10;
					int x2=csmX+csmw+20; int y2=csmY+10;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
				}
				repaint();
			}
			else if(keycode==37)
			{
				int x=lxyX;
				int y=lxyY+lxyImage.getHeight(null);
				if(mapID==1){
					//判断处理李逍遥边界问题
					if(lxyX<=0) lxyX=0;
					lxyX-=5;
					lxyIndex++;
					if(lxyIndex>=lxyLeftImages.length)
						lxyIndex=0;
					lxyImage=lxyLeftImages[lxyIndex];
					lxyDir=KeyEvent.VK_LEFT;
					//判断李逍遥是否进入了障碍物区域
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入阿旺婶的边界区域
					int x0=lxyX; int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX; int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入了旺财嫂的区域
					x0=lxyX; y0=lxyY+lxyImage.getHeight(null);
					x2=wcsX+wcsImages[wcsIndex].getWidth(null)-5;
					y2=wcsY+wcsImages[wcsIndex].getHeight(null)+100;
					x1=x2-20; y1=wcsY+20;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入了传送门边界
					x0=lxyX; y0=lxyY;
					x2=csmX+csmw;
					y2=csmY+csmh+5;
					x1=csmX; y1=csmY-20;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
				}
				else if(mapID==2){
					/*ljcX+=5;awsX+=5;azX+=5;
					wcsX+=5;xhX+=5;mjX+=5;
					xjX+=5;xxjX+=5;*/
					if(lxyX<=0) lxyX=0;
					lxyX-=5;
					lxyIndex++;
					if(lxyIndex>=lxyLeftImages.length)
						lxyIndex=0;
					lxyImage=lxyLeftImages[lxyIndex];
					lxyDir=KeyEvent.VK_LEFT;
					//判断李逍遥是否进入障碍物区域
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入阿兰的边界
					int x0=lxyX; int y0=lxyY;
					int x1=alX; int y1=alY;
					int x2=alX+alw; int y2=alY+alh;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入小二的边界
					x1=xeX; y1=xeY;
					x2=xeX+xew; y2=xeY+xeh;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//判断李逍遥向左走的时候是否进入传送门边界
					x0=lxyX; y0=lxyY;
					x2=csmX+csmw;
					y2=csmY+csmh+5;
					x1=csmX; y1=csmY-100;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
				}
				repaint();
			}
			else if(keycode==39)
			{
				int x=lxyX+lxyImage.getWidth(null);
				int y=lxyY+lxyImage.getHeight(null);
				if(mapID==1){
					//判断处理李逍遥边界问题
					if(lxyX>=ljcImage.getWidth(null)-lxyImage.getWidth(null))
						lxyX=ljcImage.getWidth(null)-lxyImage.getWidth(null);
					lxyX+=5;
					lxyIndex++;
					if(lxyIndex>=lxyRightImages.length)
						lxyIndex=0;
					lxyImage=lxyRightImages[lxyIndex];
					lxyDir=KeyEvent.VK_RIGHT;
					//判断李逍遥是否进入了障碍物区域
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走的时候是否进入了阿旺婶边界区域
					int x0=lxyX+lxyImage.getWidth(null); 
					int y0=lxyY+lxyImage.getHeight(null);
					int x2=awsX+5;
					int y2=awsY+awsImages[awsIndex].getHeight(null)+50;
					int x1=awsX+awsImages[awsIndex].getWidth(null)-25; 
					int y1=awsY+25;
					if(x0<x1&&x0>x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走的时候是否进入了小孩边界区域
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY+lxyImage.getHeight(null);
					x2=xhX+20; y2=xhY+20;
					x1=x2+100; y1=xhY+xhImages[xhIndex].getHeight(null)+20;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走的时候是否进入了传送门的边界
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY;
					x1=csmX-5; y1=csmY-20;
					x2=csmX+10; y2=csmY+csmh+20;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
				}
				else if(mapID==2){
					/*ljcX-=5;awsX-=5;azX-=5;
					wcsX-=5;xhX-=5;mjX-=5;
					xjX-=5;xxjX-=5;*/
					if(lxyX>=ljcscImages[ljcscIndex].getWidth(null)-lxyImage.getWidth(null))
						lxyX=ljcscImages[ljcscIndex].getWidth(null)-lxyImage.getWidth(null);
					lxyX+=5;
					lxyIndex++;
					if(lxyIndex>=lxyRightImages.length)
						lxyIndex=0;
					lxyImage=lxyRightImages[lxyIndex];
					lxyDir=KeyEvent.VK_RIGHT;
					//判断李逍遥是否进入障碍物区域
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走的时候是否进入了小二的边界
					int x0=lxyX+lxyImage.getWidth(null); int y0=lxyY;
					int x1=xeX; int y1=xeY-20;
					int x2=xeX+xew; int y2=xeY+xeh+10;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走时是否进入阿紫的边界
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY;
					x1=aziX+5; y1=aziY;
					x2=aziX+aziw; y2=aziY+azih;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
					//判断李逍遥向右走的时候是否进入了传送门的边界
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY;
					x1=csmX-5; y1=csmY-100;
					x2=csmX+10; y2=csmY+csmh+20;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
				}
				repaint();
			}
			//用空格键控制聊天窗口
			else if(keycode==KeyEvent.VK_SPACE)
			{
				if(mapID==1){
					int x=lxyX+lxyImage.getWidth(null); 
					int y=lxyY+lxyImage.getHeight(null);
					int x2=awsX;
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					int x1=x2+20; int y1=y2-20;
					if(lxyDir==KeyEvent.VK_RIGHT&&x<x1&&x>x2&&y>y1&&y<y2)
					{
					    isltawsShow=true;
					}
					x=lxyX; y=lxyY;
					x2=azX+azImages[azIndex].getWidth(null);
					y2=azY;
					x1=x2-100; y1=y2+100;
					if(lxyDir==KeyEvent.VK_UP&&x>x1&&x<x2&&y<y1&&y>y2)
					{
						isltazShow=true;
					}
					x=lxyX; y=lxyY+lxyImage.getHeight(null);
					x2=wcsX+wcsImages[wcsIndex].getWidth(null);
					y2=wcsY+wcsImages[wcsIndex].getHeight(null);
					x1=x2-20; y1=y2-20 ;
					if(lxyDir==KeyEvent.VK_LEFT&&x>x1&&x<x2&&y>y1&&y<y2)
					{
						isltwcsShow=true;
					}
					x=lxyX+lxyImage.getWidth(null);
					y=lxyY+lxyImage.getHeight(null);
					x2=xhX;
					y2=xhY;
					x1=x2+100; y1=y2+100;
					if(lxyDir==KeyEvent.VK_RIGHT&&x<x1&&x>x2&&y<y1&&y>y2)
					{
						isltxhShow=true;
					}
				}
				else if(mapID==2){
					int x=lxyX;int y=lxyY;
					int x2=alX;
					int y2=alY+alh/2;
					int x1=x2+alw;
					int y1=y2+alh-10;
					if(lxyDir==KeyEvent.VK_UP&&x>x2&&x<x1&&y>y2&&y<y1)
					{
						isltalShow=true;
					}
					x2=xeX; y2=xeY+xeh/2;
					x1=x2+xew; y1=y2+xeh;
					if(lxyDir==KeyEvent.VK_UP&&x>x2&&x<x1&&y>y2&&y<y1)
					{
						isltxeShow=true;
					}
					x=lxyX+lxyImage.getWidth(null);
					y=lxyY;
					x1=aziX; y1=aziY;
					x2=aziX+20; y2=y1+azih/2;
					if(lxyDir==KeyEvent.VK_RIGHT&&x>x1&&x<x2&&y>y1&&y<y2)
					{
						isltaziShow=true;
					}
				}
				repaint();
			}
			//
			//判断处理是否进行切换场景
			else if(keycode==KeyEvent.VK_ENTER){
				//
				int x0=lxyX; int y0=lxyY;
				int x2=csmX+csmw+5;
				int y2=csmY+csmh+5;
				int x1=csmX; int y1=csmY-10;
				if(mapID==1){
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2){
						mapID=2;
					}
				}
				else if(mapID==2){
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2){
						mapID=1;
					}
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
