
//Controlador da Tela de Entradas e Saídas

package br.com.RosaCake.views;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import br.com.RosaCake.bean.Lancamento;
import br.com.RosaCake.conexao.Conexao;
import br.com.RosaCake.dao.Lancamentos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class InOutController implements Initializable {

    @FXML
    private ComboBox<String> comboInOutLancamento;

    @FXML
    private TextField txtValorLancamento;

    @FXML
    private TextField txtDescLancamento;

    @FXML
    private Button btnLancar;

    @FXML
    private TableView<Lancamento> tableInOut;

    @FXML
    private TableColumn<Lancamento, String> columnTipo;

    @FXML
    private TableColumn<Lancamento, Double> columnValor;

    @FXML
    private TableColumn<Lancamento, String> columnDesc;

    @FXML
    private TableColumn<Lancamento, String> columnData;
    
    @FXML
    private TableColumn<Lancamento, String> columnPagamento;

    @FXML
    private TableColumn<Lancamento, String> columnParcelas;

    @FXML
    private ComboBox<String> comboInOutFiltro;

    @FXML
    private DatePicker txtDataFiltro;

    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnResetar;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnLucros;
    
    @FXML
    private Label lblErroValor;
    
    @FXML
    private DatePicker txtData;
    
    @FXML
    private TextArea txtArea;
    
    @FXML
    private CheckBox checkCartao;

    @FXML
    private CheckBox checkDinheiro;

    @FXML
    private ComboBox<String> comboCartao;
    
    @FXML
    private Label lblErroCartao;
    
    @FXML
    private TextField txtDescFiltro;

    @FXML
    private ComboBox<Integer> comboParcelas;
    
    private List<String> inOut = new ArrayList<String>();
    
    private List<String> cartaoCombo = new ArrayList<String>();
    
    private List<Integer> parcelasCombo = new ArrayList<Integer>();
    
    private ObservableList<String> obsInOut;
    
    private ObservableList<String> obsCartao;
    
    private ObservableList<Integer> obsParcelas;
    
    private ObservableList<Lancamento> obsTable1;
    
    String data;
    
    boolean saiu = false;
    
    //Fazer as seguintes ações assim que a tela inicializar
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	btnLancar.setDisable(true);
    	
    	//Deixar o loop rodando em paralelo com o resto do programa, para verificar se todos os campos foram preenchido
    	new Thread() {
    		
    		@Override
    		public void run() {
    			while(true) {
    				if(saiu == false) {
    					//Se algum campo não foi preenchido, o botao de lancar ira continuar desabilitado
    					if(txtValorLancamento.getText() == null || txtDescLancamento.getText() == null || comboInOutLancamento.getSelectionModel().getSelectedItem() == null) {
	    				
		    			}else {
		    				btnLancar.setDisable(false);
		    			}
    				}else {
    					break;
    				}
	    			
    			}
    		}
    		
    	}.start();
    	
    	
    	atualizarTabela1();
    	
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	Date date1 = new Date(System.currentTimeMillis());
    	
    	data = df.format(date1);
    	
    	//Preencher os combobox
    	inOut.add("Entrada");
    	inOut.add("Saída");
    	cartaoCombo.add("Pag/Credito");
    	cartaoCombo.add("Pag/Débito");
    	cartaoCombo.add("Ton/Crédito");
    	cartaoCombo.add("Ton/Débito");
    	parcelasCombo.add(2);
    	parcelasCombo.add(3);
    	parcelasCombo.add(4);
    	parcelasCombo.add(5);
    	parcelasCombo.add(6);
    	parcelasCombo.add(7);
    	parcelasCombo.add(8);
    	parcelasCombo.add(9);
    	parcelasCombo.add(10);
    	parcelasCombo.add(11);
    	parcelasCombo.add(12);
    	
    	obsInOut = FXCollections.observableArrayList(inOut);
    	obsCartao = FXCollections.observableArrayList(cartaoCombo);
    	obsParcelas = FXCollections.observableArrayList(parcelasCombo);
    	
    	comboInOutLancamento.setItems(obsInOut);
    	comboInOutFiltro.setItems(obsInOut);
    	comboCartao.setItems(obsCartao);
    	comboParcelas.setItems(obsParcelas);
    	
    }
    
    //Detectar o click do mouse no checkBox
    @FXML
    void clicouCartao(MouseEvent event) {
    	
    	//Se o cartao for selecionado, habilite os comboBox abaixo
    	if(checkCartao.isSelected()) {
	    	comboCartao.setDisable(false);
	    	comboParcelas.setDisable(false);
	    	checkDinheiro.setSelected(false);
	    }else {
	    	comboCartao.setDisable(true);
	    	comboParcelas.setDisable(true);
	    }
    }
    
    @FXML
    void clicouDinheiro(MouseEvent event) {
    	if(checkDinheiro.isSelected()) {
	    	checkCartao.setSelected(false);
	    	comboCartao.setDisable(true);
	    	comboParcelas.setDisable(true);
	    }else {
	    	
	    }
    }
    
    //Detectar click na tabela
    @FXML
    void clicouTabela(MouseEvent event) {
    	tableInOut.setOnMouseClicked(e ->{
    		
    		//Se o usuario deu dois clicks, preecha na txtArea a descrição daquela linha
    	    if(e.getClickCount() == 2) {
    	        txtArea.setText(tableInOut.getSelectionModel().getSelectedItem().getDescricao());
    	    }
    	     else {
    	        			
    	     }
    	});

    }
    
    //Detectar um tecla pressionada no campo de Data do Filtro
    @FXML
    void enterDataF(KeyEvent event) throws ParseException {

    	//Se a tecla pressionada for enter
    	if(event.getCode() == KeyCode.ENTER) {
    		
    		//Se o botao de filtrar estiver desabilitado não faça nada
    		if(btnFiltrar.isDisable()) {
    		
    		}else {//Se estiver Habilitado, filtre.
    			filtrar();
    		}
    	}else {
    		
    	}
    	
    }
    
    @FXML
    void enterDescricaoF(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnFiltrar.isDisable()) {
    		
    		}else {
    			filtrar();
    		}
    	}else {
    		
    	}
    	
    }
    
    @FXML
    void enterTipoF(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnFiltrar.isDisable()) {
    		
    		}else {
    			filtrar();
    		}
    	}else {
    		
    	}
    	
    }
    
    @FXML
    void enterCartao(KeyEvent event) throws ParseException {
    	
    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterCartaoTipo(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterData(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterDescricao(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterDinheiro(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterParcelas(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterTipo(KeyEvent event) throws ParseException {

    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }

    @FXML
    void enterValor(KeyEvent event) throws ParseException {
    	
    	if(event.getCode() == KeyCode.ENTER) {
    		if(btnLancar.isDisable()) {
    		
    		}else {
    			lancar();
    		}
    	}else {
    		
    	}
    	
    }
    
    private void atualizarTabela1() {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    	columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	columnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    	columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
    	columnPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    	columnParcelas.setCellValueFactory(new PropertyValueFactory<>("parcelas"));
    	
    	obsTable1 = FXCollections.observableArrayList(o.consultarLancamentos(con));
    	tableInOut.setItems(obsTable1);
    	
    }
    
    
    @FXML
    void clicouFiltrar(ActionEvent event) throws ParseException {
    	
    	filtrar();
    	
    }

    private void lancar() throws ParseException {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	String dataLancar = data;
    	boolean cartaoSelecionado = true;
    	int parcelas = 0;
    	String formaPagamento = "Dinheiro";
    	String numeroParcelas = "";
    	
    	//Se o que estiver escrito no campo de valor for um numero...
    	if(isNumero(txtValorLancamento.getText())) {
    		
    		//Se o campo de data nao tiver sido preenchido, deixe a data como a de hoje
    		if(txtData.getValue() == null) {
    			
    		}else {
    			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    			
    			//Pegar o valor do campo de data
    	    	Date date1 = Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	    	
    	    	//Transformando o valor pego em String
    			dataLancar = df.format(date1);
    		}
    		lblErroValor.setVisible(false);
    		
    		double valor = Double.parseDouble(txtValorLancamento.getText());
    		double valorAux = valor;
    		
    		//Se o cartão for selecionado
    		if(checkCartao.isSelected()) {
    			
    			//Se o usuário nao tiver selecionado o tipo do cartao e a forma de pagamento, aparecerá uma mensagem de erro
    			if(comboCartao.getSelectionModel().getSelectedItem() == null) {
    				lblErroCartao.setVisible(true);
					cartaoSelecionado = false;
    			}
    			else {
    				//Possíveis opções do usuário
    				switch (comboCartao.getSelectionModel().getSelectedItem()) {
    			
					case "Pag/Credito":
						
						formaPagamento = "Pag/Credito";
						
						if(comboParcelas.getSelectionModel().getSelectedItem() == null) {
							numeroParcelas = "1x";
							valorAux = valor - (valor * 0.0478);
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 2) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.0409);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 3) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.0541);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 4) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.0670);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 5) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.0796);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 6) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.0920);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 7) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1041);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 8) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1161);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 9) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1278);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 10) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1392);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 11) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1505);
							
						}
						else if(comboParcelas.getSelectionModel().getSelectedItem() == 12) {
							numeroParcelas = comboParcelas.getSelectionModel().getSelectedItem() + "x";
							valorAux = valor - (valor * 0.0531);
							valorAux = valorAux - (valor * 0.1615);
							
						}
						
						lblErroCartao.setVisible(false);
						cartaoSelecionado = true;
						break;

					case "Pag/Débito":
						
						formaPagamento = "Pag/Débito";
						
						valorAux = valor - (valor * 0.0199);
						lblErroCartao.setVisible(false);
						cartaoSelecionado = true;
						break;
					
					case "Ton/Crédito":
						
						formaPagamento = "Ton/Crédito";
						
						valorAux = valor - (valor * 0.0498);
						
						if(comboParcelas.getSelectionModel().getSelectedItem() == null) {
							numeroParcelas = "1x";
						}
						else {
							parcelas = comboParcelas.getSelectionModel().getSelectedItem();
							numeroParcelas = parcelas + "x";
							valorAux = valorAux - (valor * (0.0199 * parcelas));
						}
						
						lblErroCartao.setVisible(false);
						cartaoSelecionado = true;
						break;
					
					case "Ton/Débito":
						
						formaPagamento = "Ton/Débito";
						
						valorAux = valor - (valor * 0.0199);
						lblErroCartao.setVisible(false);
						cartaoSelecionado = true;
						break;	
						
					default:
						break;
    				}
    			}
    			
    		}
        	
    		//Se ocorreu tudo bem, insira no banco
    		if(cartaoSelecionado) {
    			o.inserir(comboInOutLancamento.getSelectionModel().getSelectedItem(), valorAux, txtDescLancamento.getText(), dataLancar, formaPagamento, numeroParcelas, con);
        	
    			atualizarTabela1();
        	
    			//Depois de inserir, resete todos os campos
    			
    			comboInOutLancamento.getSelectionModel().clearSelection();
    			comboCartao.getSelectionModel().clearSelection();
    			comboParcelas.getSelectionModel().clearSelection();
    			checkCartao.setSelected(false);
    			checkDinheiro.setSelected(false);
    			txtData.setValue(null);
    			txtValorLancamento.setText(null);
    			txtDescLancamento.setText(null);
    			btnLancar.setDisable(true);
    			comboCartao.setDisable(true);
    			comboParcelas.setDisable(true);
    		}else {
    			
    		}
        	
        	
    	}
    	else {
    		lblErroValor.setVisible(true);
    	}
    }
    
    private void filtrar() throws ParseException {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
		String data2 = "";
		String inOut = "";
		String desc = "";
		
		if(txtDataFiltro.getValue() == null) {
			
		}
		else {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date1 = Date.from(txtDataFiltro.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			data2 = df.format(date1);
			
		}
		
		if(comboInOutFiltro.getSelectionModel().getSelectedItem() == null) {
			
		}
		else {
			inOut = comboInOutFiltro.getSelectionModel().getSelectedItem();
		}
		
		if(txtDescFiltro.getText() == null) {
			
		}
		else if(txtDescFiltro.getText().equals("")) {
			
		}
		else {
			desc = txtDescFiltro.getText();
		}
		
		List<Lancamento> lista;
		
		lista = o.filtrarConsulta(inOut, data2, desc, con);
    	
    	columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    	columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	columnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    	columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
    	columnPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    	columnParcelas.setCellValueFactory(new PropertyValueFactory<>("parcelas"));
    	
    	obsTable1 = FXCollections.observableArrayList(lista);
    	tableInOut.setItems(obsTable1);
    	
    	comboInOutFiltro.getSelectionModel().clearSelection();
    	txtDataFiltro.setValue(null);
    	txtDescFiltro.setText(null);
    	
    }
    
    @FXML
    void clicouLancar(ActionEvent event) throws ParseException {
    	
    	lancar();
    	
    }
    
    private boolean isNumero(String s) {
    	
    	boolean verificacao = false;
    	int pontoVirgula = 0;
    	
    	//String para Array de Char
    	char c[] = s.toCharArray();
    	
    	int i = 0;
    	
    	while(i < c.length) {
    		
    		//Se estiver verificando o primeio ou o ultimo digito, ele tera que ser obrigatoriamente um numero
    		if(i == 0 || i == c.length - 1) {
    			
    			if(pontoVirgula > 1) {
    				verificacao = false;
    				break;
    			}else {
    				if(Character.isDigit(c[i])) {
    					verificacao = true;
	            	}else if (c[i] == '.') { //Se o caracter atual for um ponto
	            		verificacao = false;
	            		break;
	            	}else if (c[i] == ',') { //Se o caracter atual for uma vírgula
	            		//c[i] = '.';
	            		verificacao = false;
	            		break;
	            	}else {
	            		verificacao = false;
	            		break;
	            	}
    			}
    			
    		}
    		else {
    			
    			//Se tiver mais de um ponto ou virgula...
    			if(pontoVirgula > 1) {
    				verificacao = false;
    				break;
    			}else {
    				
    				//Se o caracter atual for um digito
    	    		if(Character.isDigit(c[i])) {
    	        		verificacao = true;
    	        	}else if (c[i] == '.') { //Se o caracter atual for um ponto
    	        		verificacao = true;
    	        		pontoVirgula++;
    	        	}else if (c[i] == ',') { //Se o caracter atual for uma vírgula
    	        		c[i] = '.';
    	        		verificacao = true;
    	        		pontoVirgula++;
    	        	}else {
    	        		verificacao = false;
    	        		break;
    	        	}
    				
    			}
    		}
    		
    		i++;
    		
    	}
    	
    	if(verificacao) {
    		//Array de Char para String
        	String r = new String(c);
        	
        	//Muda a String do campo Valor
        	txtValorLancamento.setText(r);
    	}
    	
    	
    	return verificacao;
    }
    
    @FXML
    void clicouResetar(ActionEvent event) {
    	atualizarTabela1();
    	
    	comboInOutFiltro.getSelectionModel().clearSelection();
    	txtData.setValue(null);
    }

   
    
    @FXML
    void pressionouTecla(KeyEvent event) throws ParseException {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	String tipo = tableInOut.getSelectionModel().getSelectedItem().getTipo();
    	String desc = tableInOut.getSelectionModel().getSelectedItem().getDescricao();
    	double valor = tableInOut.getSelectionModel().getSelectedItem().getValor();
    	String dataGet = tableInOut.getSelectionModel().getSelectedItem().getData();
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(dataGet).getTime());
    	
    	if(event.getCode() == KeyCode.DELETE) {
    		
    		o.removerLancamento(tipo, valor, desc, date, con);
    		
    		atualizarTabela1();
    		
    	}else {
    		
    	}
    }

    @FXML
    void clicouVoltar(ActionEvent event) {
    	saiu = true;
    	
    	try {
			new ProximaTela("Inicial.fxml").start(Rodar.stage);
		} catch (Exception err) {
			err.printStackTrace();
		}
    }
    
    @FXML
    void clicouLucros(ActionEvent event) {
    	try {
			new ProximaTela("Lucros.fxml").start(Rodar.stage);
		} catch (Exception err) {
			err.printStackTrace();
		}
    }

}