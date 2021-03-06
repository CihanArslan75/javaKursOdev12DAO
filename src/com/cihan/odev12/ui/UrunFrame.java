package com.cihan.odev12.ui;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cihan.odev12.dao.UrunDao;
import com.cihan.odev12.dao.UrunStokDao;
import com.cihan.odev12.dao.UrunTipiDao;
import com.cihan.odev12.model.Urun;
import com.cihan.odev12.model.UrunStok;
import com.sun.xml.internal.ws.api.Component;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UrunFrame extends JFrame{
	private JTable table;
	private JTextField txtAdet;
	private JTextField txtBFiyat;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private String[][] dataStok;
	private JTextField txtUrunStokNo;
	private Integer idS;
	private String urunAdi;
	
	public UrunFrame() {
	
	  urunInitialize();
	}

	public void urunInitialize() {
		setTitle("Ürünler ve Ürün Stokları Listesi  ");
		setBounds(100, 100, 1000, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, null, null, null));
		scrollPane.setBounds(12, 29, 950, 150);
		getContentPane().add(scrollPane);
		
		txtAdet = new JTextField();
		txtBFiyat = new JTextField();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, null, null, null));
		scrollPane_1.setBounds(12, 202, 950, 85);
		getContentPane().add(scrollPane_1);
		
		UrunDao urunDao = new UrunDao();
		
		String[][] data = null;
		String[] columnNames= {"NO","Ürün Adı","Renk","Üretim Tarihi" ,"Marka","Ürün Tipi"};
		 
		
		try {
			List<Urun> urunListe = urunDao.getUrunList();
			data = new String[urunListe.size()][9];
			Integer[] id = new Integer[urunListe.size()] ;
			for (int i = 0; i < urunListe.size(); i++) {
				
				id[i]=urunListe.get(i).getId();
				data[i][0]=""+urunListe.get(i).getId();
				data[i][1]=urunListe.get(i).getUrunadi();
				data[i][2]=urunListe.get(i).getUrunrenk();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				data[i][3]=sdf.format(urunListe.get(i).getUretimtarihi());
				data[i][4]=urunListe.get(i).getUrunmarka();
				UrunTipiDao urunTipiDao=new UrunTipiDao(); 
				String urunTipi= urunTipiDao.getUrunTipi(urunListe.get(i).getUrunid());
				data[i][5]=urunTipi;
				
			table = new JTable(data,columnNames);
	    	scrollPane.setViewportView(table);
	  
	    	UrunStokDao urunStokDao=new UrunStokDao();
			List<UrunStok> urunStokListe=new ArrayList<UrunStok>();
		
				String[][] data1 = null;
				String[] columnNames1= {"Ürün Stok NO","Ürün Adı","Ürün Beden","Ürün Adedi","Birim Fiyatı"};
				urunStokListe=urunStokDao.getUrunStok(id[0]);
				data1 = new String[urunStokListe.size()][5];
				for (int j = 0; j < urunStokListe.size() ; j++) {
					urunAdi=urunListe.get(0).getUrunadi();
					data1[j][0]=""+urunStokListe.get(j).getId();
					data1[j][1]=urunAdi;
					data1[j][2]=urunStokListe.get(j).getUrunBedenNo()+" "+urunStokListe.get(j).getUrunBeden() ;
					data1[j][3]=""+urunStokListe.get(j).getAdet();
					data1[j][4]=""+urunStokListe.get(j).getBirimFiyati();
					
				}
				table_1 = new JTable(data1,columnNames1);	
				scrollPane_1.setViewportView(table_1);
	    	
	    	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    	    @Override
	    	    public void valueChanged(ListSelectionEvent event) {
	    	         if (table.getSelectedRow() > -1) {
	    	        	UrunStokDao urunStokDao=new UrunStokDao();
	    				List<UrunStok> urunStokListe=new ArrayList<UrunStok>();
	    				try {
	    					idS=id[table.getSelectedRow()];
	    					urunStokListe=urunStokDao.getUrunStok(id[table.getSelectedRow()]);
	    					String[][] data1 = null;
	    					data1 = new String[urunStokListe.size()][5];
	    					
	    					for (int j = 0; j < urunStokListe.size() ; j++) {
		    					urunAdi=urunListe.get(table.getSelectedRow()).getUrunadi();
		    					data1[j][0]=""+urunStokListe.get(j).getId();
		    					data1[j][1]=urunAdi;
		    					data1[j][2]=urunStokListe.get(j).getUrunBedenNo()+" "+urunStokListe.get(j).getUrunBeden() ;
		    					data1[j][3]=""+urunStokListe.get(j).getAdet();
		    					data1[j][4]=""+urunStokListe.get(j).getBirimFiyati();
		    					
		    				 }
	    					dataStok=data1;
		    				table_1 = new JTable(data1,columnNames1);	
		    				scrollPane_1.setViewportView(table_1);
		    				table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    			    	    @Override
		    			    	    public void valueChanged(ListSelectionEvent event) {
		    			    	         if (table_1.getSelectedRow() > -1) {
		    			    	        	txtUrunStokNo.setText(dataStok[table_1.getSelectedRow()][0]);
		    			    	        	txtAdet.setText(dataStok[table_1.getSelectedRow()][3]);
		    			    	        	txtBFiyat.setText(dataStok[table_1.getSelectedRow()][4]);
		    			    	         }
		    			    	    }
		    			    });
						} catch (SQLException e) {
						
							e.printStackTrace();
						}
	    				
	    		
	    	        }
	    	    }

	    	});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblStokNo = new JLabel("Ürün Stok No");
		lblStokNo.setBounds(22, 311, 89, 16);
		getContentPane().add(lblStokNo);
		
		txtUrunStokNo = new JTextField();
		txtUrunStokNo.setEditable(false);
		txtUrunStokNo.setBounds(123, 308, 104, 22);
		getContentPane().add(txtUrunStokNo);
		txtUrunStokNo.setColumns(10);		
		
		JLabel lblAdedi = new JLabel("Adedi :");
		lblAdedi.setBounds(26, 352, 56, 16);
		getContentPane().add(lblAdedi);
		
		txtAdet.setBounds(123, 349, 104, 22);
		getContentPane().add(txtAdet);
		txtAdet.setColumns(10);
		
		JLabel lblBirimFiyat = new JLabel("Birim Fiyatı : ");
		lblBirimFiyat.setBounds(28, 392, 83, 16);
		getContentPane().add(lblBirimFiyat);
		
		txtBFiyat.setBounds(123, 389, 104, 22);
		getContentPane().add(txtBFiyat);
		txtBFiyat.setColumns(10);
		
		JButton btnStokUpdate = new JButton("Stok Değiştir ");
		btnStokUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UrunStokDao urunStokDao = new UrunStokDao();
		        List<Integer> urunStokListe=new ArrayList<Integer>();
		        urunStokListe.add(Integer.parseInt(txtUrunStokNo.getText()));
		        urunStokListe.add(Integer.parseInt(txtAdet.getText()));
		        urunStokListe.add(Integer.parseInt(txtBFiyat.getText()));
				try {
					String mesaj=urunStokDao.urunStokUpdate(urunStokListe);
					JOptionPane.showMessageDialog(UrunFrame.this, mesaj);
					table_1.removeAll();
					table_1.repaint();
					txtAdet.setText("");
					txtBFiyat.setText("");
					txtUrunStokNo.setText("");
					String[][] data1 = null;
					String[] columnNames1= {"Ürün Stok NO","Ürün Adı","Ürün Beden","Ürün Adedi","Birim Fiyatı"};
					List<UrunStok> urunStokListe1=urunStokDao.getUrunStok(idS);
					data1 = new String[urunStokListe1.size()][5];
					for (int j = 0; j < urunStokListe1.size() ; j++) {
						data1[j][0]=""+urunStokListe1.get(j).getId();
						data1[j][1]=urunAdi;
						data1[j][2]=urunStokListe1.get(j).getUrunBedenNo()+" "+urunStokListe1.get(j).getUrunBeden() ;
						data1[j][3]=""+urunStokListe1.get(j).getAdet();
						data1[j][4]=""+urunStokListe1.get(j).getBirimFiyati();
						
					}
					table_1 = new JTable(data1,columnNames1);	
					scrollPane_1.setViewportView(table_1);

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(UrunFrame.this, "SQL çalıştırılırken Hata Oluştu ");
				}
				
			}
		});
		btnStokUpdate.setBounds(289, 324, 183, 25);
		getContentPane().add(btnStokUpdate);
		
		JButton btnStokDelete = new JButton("Stok Sil");
		btnStokDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UrunStokDao urunStokDao = new UrunStokDao();
		        try {
					String mesaj=urunStokDao.urunStokDelete(Integer.parseInt(txtUrunStokNo.getText()));
					JOptionPane.showMessageDialog(UrunFrame.this, mesaj);
					table_1.removeAll();
					table_1.repaint();
					txtAdet.setText("");
					txtBFiyat.setText("");
					txtUrunStokNo.setText("");
			     	String[][] data1 = null;
					String[] columnNames1= {"Ürün Stok NO","Ürün Adı","Ürün Beden","Ürün Adedi","Birim Fiyatı"};
					List<UrunStok> urunStokListe1=urunStokDao.getUrunStok(idS);
					data1 = new String[urunStokListe1.size()][5];
					for (int j = 0; j < urunStokListe1.size() ; j++) {
						data1[j][0]=""+urunStokListe1.get(j).getId();
						data1[j][1]=urunAdi;
						data1[j][2]=urunStokListe1.get(j).getUrunBedenNo()+" "+urunStokListe1.get(j).getUrunBeden() ;
						data1[j][3]=""+urunStokListe1.get(j).getAdet();
						data1[j][4]=""+urunStokListe1.get(j).getBirimFiyati();
						
					}
					table_1 = new JTable(data1,columnNames1);	
					scrollPane_1.setViewportView(table_1);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(UrunFrame.this, "SQL çalıştırılırken Hata Oluştu ");
				}
				
			}
		});
					
		btnStokDelete.setBounds(289, 364, 183, 25);
		getContentPane().add(btnStokDelete);
		
		JLabel lblrnStokDetayi = new JLabel("ÜRÜN STOK DETAYI ");
		lblrnStokDetayi.setForeground(Color.RED);
		lblrnStokDetayi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnStokDetayi.setBounds(12, 182, 215, 16);
		getContentPane().add(lblrnStokDetayi);
		
		JLabel lblrnListesi = new JLabel("ÜRÜN LİSTESİ");
		lblrnListesi.setForeground(Color.RED);
		lblrnListesi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnListesi.setBounds(12, 8, 183, 16);
		getContentPane().add(lblrnListesi);
		
		
		
	}
}
