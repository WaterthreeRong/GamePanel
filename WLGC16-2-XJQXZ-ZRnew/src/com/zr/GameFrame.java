/*
 * 功能：用来表示这个游戏项目的窗口
 * 作者：ZR
 * 时间：2018.9.29
 */
package com.zr;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //1.创建游戏窗口
		//1.1创建窗口实例化对象
		//1.2设置窗口相关内容
		//1.3设置窗口可视化
		//设置窗口信息
		JFrame window=new JFrame();
		window.setSize(1024,768);
		window.setTitle("仙剑之初始version1.0");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter(){
			//当窗口打开的时候会执行该方法
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				//输入对话框
				//String xinxi=JOptionPane.showInputDialog(null,"请输入内容：");
				//消息对话对话框
				JOptionPane.showMessageDialog(null,
						"欢迎来到仙剑之初始！你的游戏角色为李逍遥。");
			}
			//当窗口正在关闭的时候它会自动执行该方法
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				//呈现出一个退出确认对话框
				int jieguo=JOptionPane.showConfirmDialog(null, "真的真的要离开吗？！",
						"退出确认",JOptionPane.YES_NO_OPTION);
				//根据玩家选择按钮进行对应的操作
				if(jieguo==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		//添加画板
		GamePanel gp=new GamePanel();
		window.add(gp);
		//启动线程
		Thread t=new Thread(gp);
		t.start();
		//声明自定义画板类中键盘监听事件的有效性
		window.addKeyListener(gp);
		gp.addKeyListener(gp);
		//窗口可视化
		window.setVisible(true);//执行完该语句之后会自动调用执行paint()
	}

}
