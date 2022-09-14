import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class TesteProduto extends JFrame {

	private JPanel contentPane;
	private JTable tabProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteProduto frame = new TesteProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public TesteProduto() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabProduto = new JTable();
		tabProduto.setBounds(138, 218, 269, -126);
		contentPane.add(tabProduto);
		getContentPane().add(tabProduto);
		listarProduto();
	}
	
	private void listarProduto() throws SQLException 
    {  	Connection con=null;
		ConexaoBanco objconexao=new ConexaoBanco();
		try
		{   
			con=objconexao.conectar();
			if(con ==null)
			{  	JOptionPane.showMessageDialog(null,"conexão não realizada");
		    }
		    else
		    {   Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM db_pedido.produto");
				    	       
		    	String[] colunasTabela= new String[]{ "ID", "Descrição", "Pontuação" };  
		    	DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);
		    	modeloTabela.addRow(new String[] {"ID", "DESCRIÇÃO", "CADASTRO"}); 
		    	if(rs != null) {
		    	    while(rs.next()) {
		    	        modeloTabela.addRow(new String[] {  
		    	                String.valueOf(rs.getInt("ID")),  
		    	                rs.getString("descricao"),  
		    	                rs.getString("data_cadastro")
		    	            }); 
		    	    }
		    	}
		    	tabProduto.setModel(modeloTabela);
		    	
		    
		    }
        }
		catch(Exception ex)
		{
			con.close();
			
		}
	}
}
