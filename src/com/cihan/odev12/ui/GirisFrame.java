package com.cihan.odev12.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.cihan.odev12.dao.UserDAO;
import com.cihan.odev12.model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GirisFrame extends JFrame{
	private JTextField txtkadi;
	private JTextField txtsifre;

	public GirisFrame() {
		Initialize();
	}
	public void Initialize() {
		setTitle("Giriş Ekranı");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131 :");
		lblKullancAd.setBounds(28, 41, 132, 16);
		getContentPane().add(lblKullancAd);
		
		JLabel lblSifre = new JLabel("\u015Eifre :");
		lblSifre.setBounds(28, 90, 56, 16);
		getContentPane().add(lblSifre);
		
		JTextField txtKullAdi = new JTextField();
		txtKullAdi.setBounds(171, 38, 116, 22);
		getContentPane().add(txtKullAdi);
		txtKullAdi.setColumns(10);
		
		JTextField txtSifre = new JTextField();
		txtSifre.setBounds(171, 87, 116, 22);
		getContentPane().add(txtSifre);
		txtSifre.setColumns(10);
		
		JButton btnGiris = new JButton("G\u0130R\u0130\u015E");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO dao= new UserDAO();
				try {
					User user = dao.getUserForName(txtKullAdi.getText());
					if(user!=null) {
						if(!user.getPassword().equals(txtSifre.getText())) {
							JOptionPane.showMessageDialog(GirisFrame.this, "Şifre Yanlış !!");
						}
						else {
							UrunFrame urun=new UrunFrame();
							urun.setVisible(true);
							GirisFrame.this.setVisible(false);
							
						}
					} else {
						JOptionPane.showMessageDialog(GirisFrame.this, "Kullanıcı Yok !!");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(GirisFrame.this, "SQL çalıŞtırılırken bir hata oldu...!!!");
				}
			}
		});
		btnGiris.setBounds(233, 159, 97, 25);
		getContentPane().add(btnGiris);
		
		JButton btnIptal = new JButton("\u0130PTAL");
		btnIptal.setBounds(75, 159, 97, 25);
		getContentPane().add(btnIptal);
	}
}
