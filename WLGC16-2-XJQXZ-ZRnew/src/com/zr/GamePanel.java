/*
 * ���ܣ�������ʾ��Ϸ��Ŀ�Ļ���
 * ���ߣ�ZR
 * ʱ�䣺2018.9.29
 * Ϊ��Ǽ��϶�̬Ч��
 * 1.���������ͼƬ���ݶ�ȡ���浽�����ڴ浱��
 * 2.ʵ��Runnable�ӿڣ���ӽӿ��еķ���
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
//��д�Զ��廭������ӻ滭����
public class GamePanel extends JPanel implements Runnable,KeyListener{
	//�������������ʾ��ǰ�������ڵı��
	//�涨��Ҵ�Ϊ1�ţ���Ҵ��г�Ϊ2��
	int mapID;
	//����ͼƬ���������
	Image ljcImage;//��ʾ��Ҵ�ͼƬ����
	int ljcX;//��Ҵ�X����
	int ljcY;//��Ҵ�Y����
	Image[] lxyUPImages;
	Image[] lxyDownImages;
	Image[] lxyLeftImages;
	Image[] lxyRightImages;
	Image lxyImage;//������ʾ����ң��ǰͼƬ����
	int lxyX;
	int lxyY;
	int lxyIndex;
	int lxyDir;
	Image[] awsImages;//����һ��ͼƬ��������������а�����ͼƬ
	int awsIndex;//�����ʾ��������ǰͼƬ��Ӧ�±�
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
	//��������ı���ͼ�����������
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
	//������������洢��Ҵ����ݵ�ͼ
	BufferedImage ljcDataMap;//BufferedImage��ΪImage������ſ��Ի�ȡͼ�ϵ���ɫ
	//��������洢������
	Image csmImage;
	int csmX;
	int csmY;
	int csmw;
	int csmh;
	//��������洢��Ҵ��г�
	Image[] ljcscImages;
	int ljcscX;
	int ljcscY;
	int ljcscIndex;
	//������������洢��Ҵ��г����ݵ�ͼ
	BufferedImage ljcscDataMap;
	//������Ҵ��г�����ͼƬ
	//������Ҵ��г������������
	Image aziImage;
	int aziX;
	int aziY;
	int aziw;
	int azih;
	//������Ҵ��г������������
	Image alImage;
	int alX;
	int alY;
	int alw;
	int alh;
	//������Ҵ��г�С���������
	Image xeImage;
	int xeX;
	int xeY;
	int xew;
	int xeh;
	//������������Ҵ��г��������������
	String[] ltalString;
	int ltalIndex;
	boolean isltalShow;
	String[] ltaziString;
	int ltaziIndex;
	boolean isltaziShow;
	String[] ltxeString;
	int ltxeIndex;
	boolean isltxeShow;
	//���췽��
	public GamePanel()
	{
		//��ʼ��mapID
		mapID=1;
		//����·������C�̷����߸�Ŀ¼��/����ʼ��·��
		//���·����Java�����е����·���ӹ������ƺ��һ��б�ܺ��濪ʼд
		//��Ҵ�
		try {
			ljcImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiJiaCun/0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ljcX=-200;
		ljcY=-200;
		//��ʼ������ı���ͼ����������
		try {
			ltImage=ImageIO.read(new File("Legend_of_Sword_and_Fairy/LiaoTian/0.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//this.getWidth()���������Ҫ�ڴ�����ʾ��֮��ſ��Ե��û�ȡ����Ӧ��ֵ
		/*ltX=(this.getWidth()-ltImages[1].getWidth(null))/2;
		ltY=this.getHeight()-ltImages[1].getHeight(null);*/
		//������������д�ڹ��캯����ʱ���ڴ�����ʾ��֮ǰ��ȡ��ֵ����ʱ�޷�ͨ��this.
		//����ȡ��ֵ������Ӧ��д��paint()������
		//��ʼ����Ҵ���ǵ���������
		isltawsShow=false;
		ltawsString=new String[] {
				"����ң�����������簡���þò�����",
				"������������ңѽ����������",
				"����ң����ѽ������ջ�����",
				"����ң����ѽ������ջ�����",
				"����ң���������ô��ѽ��һ�ж����ðɣ�",
				"��������һ�ж�ͦ�õ��ϣ�",
				"����ң��������æ������������������ȥ���Ұݷ�����",
				"����������ѽ��һ����ѽ��",
				"����ң���õģ�"};
		ltawsIndex=0;
		isltazShow=false;
		ltazString=new String[] {
				"���죺��ң���������",
				"����ң����ѽ��"};
		ltazIndex=0;
		isltwcsShow=false;
		ltwcsString=new String[] {
				"����ɩ��Ӵ������ңѽ��",
				"����ң�����ң�����ɩ��������ң��",
				"����ɩ���ջ����ɣ����Թ�����û�ԵĻ����Ҽ����ԣ�",
				"����ң�������鷳������ɩ�ӣ��ҳԹ��ˣ�лл���ĺ��⣡",
				"����ң���Ҹ�����ȥ���Ұݷ�����",
				"����ɩ���ã��ã�����·�ϵ��ģ�",
				"����ң���������ϣ�"};
		ltwcsIndex=0;
		isltxhShow=false;
		ltxhString=new String[] {
				"СŮ������ң��磡",
				"����С������ң������������",
				"��ͷС������ң�����ʲôʱ�������ȥץ��ѽ��",
				"СŮ������ң��������ȴ�Ӧ��Ҫ����ȥ�ŷ��ݵģ�",
				"СŮ������ң�����ʲôʱ�����ȥѽ��",
				"����ң���úúã������������Ҵ�Ӧ���ǡ�",
				"����ң����������һ��ŷ���ץ��ò���ѽ��",
				"����ң��������ң�����첻�����������ˡ�",
				"����ң����ң��绹���¶�Ҫ����һ��",
				"����ң������һ�п��Ҿ�ȥ�����ǣ�",
				"СŮ������ѽ��ѽ������ң����ټ���",
				"��ͷС������ң����ټ���",
				"����С�����ټ���ң��磡",
				"����ң���ټ�����"};
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
		//����i��ͼƬ��ȡ���浽�����Ӧ�ĵ�i��λ����
		//ѭ��������֪����ʹ��forѭ����δ֪����ʹ��whileѭ��
		for(int i=0;i<awsImages.length;i++){
			//ѭ�����17��ͼƬ
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
		//��Ҵ��г�
		//��ʼ������ı���ͼ����������
		ltxhIndex=0;
		isltalShow=false;
		ltalString=new String[] {
				"����������ңѽ����þ�û����������",
				"����ң����ѽ�����������ô����",
				"������������",
				"���������������һ���¾ƣ�Ҫ��Ҫ��ƿ��ȥ������",
				"����ң���ð�������һ�������ȡ��",
				"�������ã���һ�������"
		};
		ltalIndex=0;
		isltxeShow=false;
		ltxeString=new String[]{
			"С�����͹����Ǵ�������ס�갡��",
			"����ң����������ֻ������䡣"
		};
		ltxeIndex=0;
		isltaziShow=false;
		ltaziString=new String[] {
			"���ϣ��͹�Ҫ������",
			"����ң�������ˣ���ֻ�ǹ���������"
		};
		//��ʼ����Ҵ��г�
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
		//��ʼ����Ҵ��г�������
		//��ʼ������
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
		//��ʼ������
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
		//��ʼ��С��
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
	//��ʼ��ͼƬ�ز�����
    public void paint(Graphics g)
   {
    	super.paint(g);
    	if(mapID==1){
    		//�������ң����Ҵ峡���ϣ�����Ҫ�滭��Ҵ�ĳ���
    		//׼����ͼƬ�زĸ��Ƶ�Java������
        	//�滭ͼƬ�ز��������
        	ljcX=(this.getWidth()-lxyImage.getWidth(null))/2-lxyX;
        	ljcY=(this.getHeight()-lxyImage.getHeight(null))/2-lxyY;
        	//�жϴ���߽�����
        	//����Ϊһ���߼�������Ϊһ���߼��������е�һ���������е�һ������ͬʱ����
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
        	//����Ч��
        	//Ч��1������������֮���ڴ��ڳ������챳������������
        	//�������ݻ�����g.drawString("��������",x,y);
        	//Ч��2��ͨ�������ϵĿո񰴼�����Ч��1�ĳ��ֺ���ʧ
        	//Ч��3��ֻ�е������ߵ������ǰʱ�Ż�ʵ��Ч��2
        	//Ч��4��ʵ���������ݵ��л�����仰��
        	//�������챳��������Ч��
        	if(isltawsShow)
        	{
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltawsString[ltawsIndex],ltX+30,ltY+50);
        	}
        	else if(isltazShow){
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltazString[ltazIndex],ltX+30,ltY+50);
        	}
        	else if(isltwcsShow) {
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltwcsString[ltwcsIndex],ltX+30,ltY+50);
        	}
        	else if(isltxhShow) {
        		ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltxhString[ltxhIndex],ltX+30,ltY+50);
        	}
    	}
    	else if(mapID==2){
    		ljcscX=(this.getWidth()-lxyImage.getWidth(null))/2-lxyX;
        	ljcscY=(this.getHeight()-lxyImage.getHeight(null))/2-lxyY;
        	//�жϴ�����Ҵ��г��ı߽�����
        	if(ljcscY>=0) ljcscY=0;
        	else if(ljcscY<=this.getHeight()-ljcscImages[ljcscIndex].getHeight(null)) 
        		ljcscY=this.getHeight()-ljcscImages[ljcscIndex].getHeight(null);
        	if(ljcscX>=0) ljcscX=0;
        	else if(ljcscX<=this.getWidth()-ljcscImages[ljcscIndex].getWidth(null)) 
        		ljcscX=this.getWidth()-ljcscImages[ljcscIndex].getWidth(null);
    		//�������ң����Ҵ��г������ϣ�����Ҫ�滭��Ҵ��г��ĳ���
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
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltalString[ltalIndex],ltX+30,ltY+50);
        	}
    		else if(isltxeShow)
    		{
    			ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltxeString[ltxeIndex],ltX+30,ltY+50);
    		}
    		else if(isltaziShow)
    		{
    			ltX=(this.getWidth()-ltImage.getWidth(null))/2;
        		ltY=this.getHeight()-ltImage.getHeight(null);
        		g.drawImage(ltImage,ltX,ltY,this);
                g.setFont(new Font("΢���ź�",Font.BOLD,18));
        	    g.drawString(ltaziString[ltaziIndex],ltX+30,ltY+50);
    		}
    	}
   }
	@Override
	//Runnable�ӿ��ж�Ӧ�ķ���
	public void run() {
		// TODO Auto-generated method stub
		//3.��ɷ���ʵ����ǵĶ�̬Ч��
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
				//��Ҵ��г���̬Ч��
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
	//KeyListener�ӿ��ж�Ӧ�ķ���
	@Override
	public void keyPressed(KeyEvent e) {
	
		// TODO Auto-generated method stub
		//
		if(isltawsShow){
			//���������л����������
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
			//�ƶ������쿪ʼ
			//����eΪ�¼�Դ
			//ʵ�����ǵ��˶�Ч��
			//��38 ��40 ��37��39 w87 s83 a65 d68
			int keycode=e.getKeyCode();
			if(keycode==38)
			{
				int x=lxyX+lxyImage.getWidth(null)/2;
				int y=lxyY+lxyImage.getHeight(null);
				if(mapID==1){
					//�жϴ�������ң�ı߽�
					if(lxyY<=0) lxyY=0;
					lxyY-=5;
					lxyIndex++;
					if(lxyIndex>=lxyUPImages.length) 
						lxyIndex=0;
					lxyImage=lxyUPImages[lxyIndex];
					lxyDir=KeyEvent.VK_UP;
					//�ж�����ң�ϰ��﹦��
					//����ң�����ƶ�һ�κ���������ϰ�����������Ҫ����ң���˲���
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˰������ı߽�
					int x0=lxyX+lxyImage.getWidth(null)/2;
					int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX;int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˰���ı߽�
					x0=lxyX; y0=lxyY;
					x2=azX+azImages[azIndex].getWidth(null);
					y2=azY;
					x1=azX-25; y1=y2+80;
					if(x0>x1&&x0<x2&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ����ĸ���Լ�С���ǵı߽�
					x2=mjX+mjImages[mjIndex].getWidth(null)+20; 
					y2=mjY+mjImages[mjIndex].getHeight(null);
					x1=mjX-20; y1=mjY;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ŵı߽�
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
					//�ж�����ң�Ƿ�����ϰ�������
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˰����ı߽�
					int x0=lxyX; int y0=lxyY;
					int x2=alX-10; int y2=alY+alh-10;
					int x1=alX+alw; int y1=y2+5;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ������С���ı߽�
					x2=xeX-50; y2=xeY+xeh-15;
					x1=xeX+xew-10; y1=y2+10;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyY+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ŵı߽�
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
					//�жϴ�������ң�߽�����
					if(lxyY>=ljcImage.getHeight(null)-lxyImage.getHeight(null))
						lxyY=ljcImage.getHeight(null)-lxyImage.getHeight(null);
					lxyY+=5;
					lxyIndex++;
					if(lxyIndex>=lxyDownImages.length)
						lxyIndex=0;
					lxyImage=lxyDownImages[lxyIndex];
					lxyDir=KeyEvent.VK_DOWN;
					//�ж�����ң�Ƿ�������ϰ�������
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyY-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˰������߽�����
					int x0=lxyX+lxyImage.getWidth(null)/2;
					int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX;int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ����������ɩ�ı߽�����
					x1=wcsX; y1=wcsY;
					x2=wcsX+wcsImages[wcsIndex].getWidth(null);
					y2=wcsY+wcsImages[wcsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyY-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ������С���߽�����
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
					//�ж�����ң�Ƿ�����ϰ�������
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyY-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ŵı߽�
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
					//�жϴ�������ң�߽�����
					if(lxyX<=0) lxyX=0;
					lxyX-=5;
					lxyIndex++;
					if(lxyIndex>=lxyLeftImages.length)
						lxyIndex=0;
					lxyImage=lxyLeftImages[lxyIndex];
					lxyDir=KeyEvent.VK_LEFT;
					//�ж�����ң�Ƿ�������ϰ�������
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ���밢�����ı߽�����
					int x0=lxyX; int y0=lxyY+lxyImage.getHeight(null)/2;
					int x1=awsX; int y1=awsY;
					int x2=awsX+awsImages[awsIndex].getWidth(null);
					int y2=awsY+awsImages[awsIndex].getHeight(null);
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ����������ɩ������
					x0=lxyX; y0=lxyY+lxyImage.getHeight(null);
					x2=wcsX+wcsImages[wcsIndex].getWidth(null)-5;
					y2=wcsY+wcsImages[wcsIndex].getHeight(null)+100;
					x1=x2-20; y1=wcsY+20;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ű߽�
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
					//�ж�����ң�Ƿ�����ϰ�������
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ���밢���ı߽�
					int x0=lxyX; int y0=lxyY;
					int x1=alX; int y1=alY;
					int x2=alX+alw; int y2=alY+alh;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ����С���ı߽�
					x1=xeX; y1=xeY;
					x2=xeX+xew; y2=xeY+xeh;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX+=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ���봫���ű߽�
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
					//�жϴ�������ң�߽�����
					if(lxyX>=ljcImage.getWidth(null)-lxyImage.getWidth(null))
						lxyX=ljcImage.getWidth(null)-lxyImage.getWidth(null);
					lxyX+=5;
					lxyIndex++;
					if(lxyIndex>=lxyRightImages.length)
						lxyIndex=0;
					lxyImage=lxyRightImages[lxyIndex];
					lxyDir=KeyEvent.VK_RIGHT;
					//�ж�����ң�Ƿ�������ϰ�������
					if(ljcDataMap.getRGB(x, y)==-521461)
					{
						lxyX-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˰������߽�����
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
					//�ж�����ң�����ߵ�ʱ���Ƿ������С���߽�����
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY+lxyImage.getHeight(null);
					x2=xhX+20; y2=xhY+20;
					x1=x2+100; y1=xhY+xhImages[xhIndex].getHeight(null)+20;
					if(x0>x2&&x0<x1&&y0>y2&&y0<y1)
					{
						lxyX-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ŵı߽�
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
					//�ж�����ң�Ƿ�����ϰ�������
					if(ljcscDataMap.getRGB(x, y)==-65536)
					{
						lxyX-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ������С���ı߽�
					int x0=lxyX+lxyImage.getWidth(null); int y0=lxyY;
					int x1=xeX; int y1=xeY-20;
					int x2=xeX+xew; int y2=xeY+xeh+10;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
					//�ж�����ң������ʱ�Ƿ���밢�ϵı߽�
					x0=lxyX+lxyImage.getWidth(null);
					y0=lxyY;
					x1=aziX+5; y1=aziY;
					x2=aziX+aziw; y2=aziY+azih;
					if(x0>x1&&x0<x2&&y0>y1&&y0<y2)
					{
						lxyX-=5;
					}
					//�ж�����ң�����ߵ�ʱ���Ƿ�����˴����ŵı߽�
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
			//�ÿո���������촰��
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
			//�жϴ����Ƿ�����л�����
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
