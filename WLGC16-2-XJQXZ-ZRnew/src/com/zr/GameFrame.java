/*
 * ���ܣ�������ʾ�����Ϸ��Ŀ�Ĵ���
 * ���ߣ�ZR
 * ʱ�䣺2018.9.29
 */
package com.zr;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //1.������Ϸ����
		//1.1��������ʵ��������
		//1.2���ô����������
		//1.3���ô��ڿ��ӻ�
		//���ô�����Ϣ
		JFrame window=new JFrame();
		window.setSize(1024,768);
		window.setTitle("�ɽ�֮��ʼversion1.0");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter(){
			//�����ڴ򿪵�ʱ���ִ�и÷���
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				//����Ի���
				//String xinxi=JOptionPane.showInputDialog(null,"���������ݣ�");
				//��Ϣ�Ի��Ի���
				JOptionPane.showMessageDialog(null,
						"��ӭ�����ɽ�֮��ʼ�������Ϸ��ɫΪ����ң��");
			}
			//���������ڹرյ�ʱ�������Զ�ִ�и÷���
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				//���ֳ�һ���˳�ȷ�϶Ի���
				int jieguo=JOptionPane.showConfirmDialog(null, "������Ҫ�뿪�𣿣�",
						"�˳�ȷ��",JOptionPane.YES_NO_OPTION);
				//�������ѡ��ť���ж�Ӧ�Ĳ���
				if(jieguo==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		//��ӻ���
		GamePanel gp=new GamePanel();
		window.add(gp);
		//�����߳�
		Thread t=new Thread(gp);
		t.start();
		//�����Զ��廭�����м��̼����¼�����Ч��
		window.addKeyListener(gp);
		gp.addKeyListener(gp);
		//���ڿ��ӻ�
		window.setVisible(true);//ִ��������֮����Զ�����ִ��paint()
	}

}
