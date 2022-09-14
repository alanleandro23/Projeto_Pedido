import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;

public class Produto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDescricao;
	private JTable tabProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produto frame = new Produto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Produto() {
		setTitle("Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Testar Conexão");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexaoBanco objConexao=new ConexaoBanco();
				Connection con= objConexao.conectar();
				
				if(con==null)
					JOptionPane.showMessageDialog(null,"Não possivel conectar no banco!");
				else
					JOptionPane.showMessageDialog(null,"Banco conectado");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(551, 11, 143, 23);
		contentPane.add(btnNewButton);
		
		JLabel txtID = new JLabel("ID");
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtID.setBounds(10, 57, 21, 14);
		contentPane.add(txtID);
		
		textField = new JTextField();
		textField.setBounds(37, 55, 132, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel txtDataCadastro = new JLabel("Data Cadastro");
		txtDataCadastro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDataCadastro.setBounds(179, 58, 91, 14);
		contentPane.add(txtDataCadastro);
		
		textField_1 = new JTextField();
		textField_1.setBounds(267, 55, 154, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(88, 124, 197, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Descricação");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 127, 68, 14);
		contentPane.add(lblNewLabel);
		
		JButton bntNovo = new JButton("Novo");
		bntNovo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bntNovo.setBounds(295, 123, 89, 23);
		contentPane.add(bntNovo);
		
		JButton bntCadastrar = new JButton("Cadastrar");
		bntCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bntCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bntCadastrar.setBounds(394, 123, 89, 23);
		contentPane.add(bntCadastrar);
		
		JButton bntExcluir = new JButton("Excluir");
		bntExcluir.setBounds(493, 123, 89, 23);
		contentPane.add(bntExcluir);
		
		JButton bntAtualizar = new JButton("Atualizar");
		bntAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bntAtualizar.setBounds(592, 123, 89, 23);
		contentPane.add(bntAtualizar);
		
		tabProduto = new JTable();
		tabProduto.setBounds(135, 179, 462, 159);
		contentPane.add(tabProduto);
		private void listarProduto() throws SQLException
		{ Connection con=null;
		ConexaoBanco objconexao=new ConexaoBanco();
		con=objconexao.conectar();
		if(con ==null)
		{ JOptionPane.showMessageDialog(null,"conexão não realizada");
		}
		else
		{ Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM db_pedido.produto");
		String[] colunasTabela = new String[]{ "ID", "Descrição", "Pontuação" };
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
	
	}
	
}
