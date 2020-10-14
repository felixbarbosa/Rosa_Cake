
//Controlador da tela de Lucros

package br.com.RosaCake.views;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.RosaCake.bean.Lancamento;
import br.com.RosaCake.bean.Lucro;
import br.com.RosaCake.bean.LucroAno;
import br.com.RosaCake.bean.LucroMes;
import br.com.RosaCake.bean.LucroSemana;
import br.com.RosaCake.conexao.Conexao;
import br.com.RosaCake.dao.Lancamentos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LucrosController implements Initializable {

	@FXML
    private TableView<Lucro> tableLucro;

    @FXML
    private TableColumn<Lucro, Double> columnLucro;

    @FXML
    private TableColumn<Lucro, String> columnDia;
    
    @FXML
    private TableView<LucroSemana> tableSemana;

    @FXML
    private TableColumn<LucroSemana, Double> columnLucroSemana;

    @FXML
    private TableColumn<LucroSemana, Integer> columnSemana;

    @FXML
    private TableView<LucroMes> tableLucroMes;

    @FXML
    private TableColumn<LucroMes, Double> columnLucroMes;

    @FXML
    private TableColumn<LucroMes, Integer> columnMes;

    @FXML
    private TableView<LucroAno> tableAno;

    @FXML
    private TableColumn<LucroAno, Double> columnLucroAno;

    @FXML
    private TableColumn<LucroAno, Integer> columnAno;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnFecharSemana;

    @FXML
    private Button btnFecharMes;

    @FXML
    private Button btnFecharAno;
    
    @FXML
    private DatePicker txtDataDia;
    
    @FXML
    private Label lblErroDia;
    
    @FXML
    private Button btnVoltar;

    private ObservableList<Lucro> obsTable2;
    
    private ObservableList<LucroSemana> obsTable3;
    
    private ObservableList<LucroMes> obsTable4;
    
    private ObservableList<LucroAno> obsTable5;
    
    private double calculoDia = 0.0;
    
    private double calculoSemana = 0.0;
    
    private double calculoMes = 0.0;
    
    private double calculoAno = 0.0;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	btnFechar.setDisable(true);
    	btnFecharAno.setDisable(true);
    	btnFecharMes.setDisable(true);
    	btnFecharSemana.setDisable(true);
    	
    	Lancamentos l = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	
    	
		
    		new Thread() {
    			public void run() {
    				try {
    					while(true) {
    						
    						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");	
    				    	Date data = new Date(System.currentTimeMillis());
    						String dataString = df.format(data);
    						
    						if(txtDataDia.getValue() == null) {
    							
    						}else {
    							data = Date.from(txtDataDia.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    							dataString = df.format(data);
    						}
    						
    							calculoDia = verificarFechamentoDia();
								calculoAno = verificarFechamentoAno();
					    		calculoMes = verificarFechamentoMes();
					    		calculoSemana = verificarFechamentoSemana();
					    		
					    		if(calculoDia != -100000.0 ) {
					    			if(l.filtrarLucroDia(dataString, con) == 0) {
					    				btnFechar.setDisable(false);
					    			}
					    			else {
					    				btnFechar.setDisable(true);
					    			}
					        	}else {
					        		btnFechar.setDisable(true);
					        	}
					        				
					        	if(calculoAno != -100000.0 ) {
					       			btnFecharAno.setDisable(false);
					        	}else {
					        		btnFecharAno.setDisable(true);
					        	}
					        				
					        	if(calculoMes != -100000.0 ) {
					        		btnFecharMes.setDisable(false);
					        	}else {
					        		btnFecharMes.setDisable(true);
					        	}
					        				
					        	if(calculoSemana != -100000.0 ) {
					        		btnFecharSemana.setDisable(false);
					        	}else {
					        		btnFecharSemana.setDisable(true);
					       		}
    						
    						
    						
    					}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			};
    		}.start();
    			
    	atualizarTabelaLucroDia();
    	atualizarTabelaLucroSemana();
    	atualizarTabelaLucroMes();
    	atualizarTabelaLucroAno();
    	
    }
    
    private void atualizarTabelaLucroDia() {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	columnLucro.setCellValueFactory(new PropertyValueFactory<>("lucro"));
    	columnDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
    	
    	obsTable2 = FXCollections.observableArrayList(o.consultarLucros(con));
    	tableLucro.setItems(obsTable2);
    	
    }
    
    private void atualizarTabelaLucroSemana() {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	columnLucroSemana.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	columnSemana.setCellValueFactory(new PropertyValueFactory<>("semana"));
    	
    	obsTable3 = FXCollections.observableArrayList(o.consultarLucroSemana(con));
    	tableSemana.setItems(obsTable3);
    	
    }
    
    private void atualizarTabelaLucroMes() {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	columnLucroMes.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	columnMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
    	
    	obsTable4 = FXCollections.observableArrayList(o.consultarLucroMes(con));
    	tableLucroMes.setItems(obsTable4);
    	
    }
    
    private void atualizarTabelaLucroAno() {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	columnLucroAno.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
    	
    	obsTable5 = FXCollections.observableArrayList(o.consultarLucroAno(con));
    	tableAno.setItems(obsTable5);
    	
    }
    
    private double verificarFechamentoDia() throws ParseException {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	Date date1 = null;
    	
    	//Pegar a data de hoje
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
    	date1 = new Date(System.currentTimeMillis());
    	String hoje = df.format(date1);
    	
    	if(txtDataDia.getValue() == null) {
    		
    	}else {
	    	date1 = Date.from(txtDataDia.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			hoje = df.format(date1);
    	}
    	
    	List<Lancamento> listaDia = o.filtrarConsulta("", hoje, "", con);
    	
    	int size = listaDia.size();
    	int i = 0;
    	
    	double calculo = 0;
    	double aux = 0;
    	
    	if(size > 0) {
    		
	    	while(i < size) {
				
	    		//Se a data for igual a de hoje
				if(listaDia.get(i).getData().equals(hoje)) {
	    			
	    			if(listaDia.get(i).getTipo().equals("Saída")) {
	    				aux = listaDia.get(i).getValor() * (-1.0);
	    				//listaCalculo.add(auxiliar);
	    			}else {
	    				//listaCalculo.add(listaConsulta.get(i).getValor());
	    				aux = listaDia.get(i).getValor();
	    			}
	    			
	    			calculo = calculo + aux;
	    			i++;
	    			
				}else { //Se não for hoje
					i++;
				}
				
			}
	    	return calculo;
	    	
    	}else { //Se ainda não possuir dias cadastrados
    		
    		return -100000.0;
    		
    	}
    	
    }
    
    @FXML
    void clicouFechar(ActionEvent event) throws ParseException {
    	
    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	Date date1 = new Date(System.currentTimeMillis());
		
    	if(txtDataDia.getValue() == null) {
    		
    	}else {
    		calculoDia = verificarFechamentoDia();
    		date1 = Date.from(txtDataDia.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	}
    	
		BigDecimal bd = new BigDecimal(calculoDia).setScale(2, RoundingMode.HALF_EVEN);
		
		calculoDia = bd.doubleValue();
    	
    	String hoje = df.format(date1);
    	
		java.sql.Date date = new java.sql.Date(df.parse(hoje).getTime());
    	
		if(o.inserirLucro(calculoDia, date, con)) {
			lblErroDia.setVisible(false);
			atualizarTabelaLucroDia();
		}
		else {
			lblErroDia.setVisible(true);
		}
    	
    }
    
    private double verificarFechamentoAno() {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	int totalAno = o.totalAnos(con);
    	int totalMeses = o.totalMeses(con);
    	int i = 0;
    	int j = 0;
    	
    	double lucroAno = 0;
    	
    	List<LucroMes> listaLucroMes = new ArrayList<LucroMes>();
    	
    	listaLucroMes = o.consultarLucroMes(con);
    	
    	if(totalAno <= 0) {
    		
    		if(totalMeses < 12) {
    			
    			return -100000.0;
    			
    		}else {
    			
    			while(i < 12) {
        			
        			lucroAno = lucroAno + listaLucroMes.get(i).getValor();
        			i++;
    				
    			}
    			
    			return lucroAno;
    			
    		}
    		
    	}
    	else {
    		
    		int aux = totalMeses/12;
    		int aux2 = (totalAno*12);
    		
    		if(aux >= totalAno + 1) {
    			
    			while(j < 12) {
    				
    				lucroAno = lucroAno + listaLucroMes.get(aux2).getValor();
        			j++;
    				aux2++;
    			}
    			
    			return lucroAno;
    		}
    		else {
    			return -100000.0;
    		}
    		
    	}
    }
    
    @FXML
    void clicouFecharAno(ActionEvent event) {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	BigDecimal bd = new BigDecimal(calculoAno).setScale(2, RoundingMode.HALF_EVEN);
		
		calculoAno = bd.doubleValue();
    	
    	o.inserirLucroAno(calculoAno, con);
		
		atualizarTabelaLucroAno();
    	
    }
    
    private double verificarFechamentoMes() {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	int totalMeses = o.totalMeses(con);
    	int totalSemanas = o.totalSemanas(con);
    	int i = 0;
    	int j = 0;
    	
    	double lucroMes = 0;
    	
    	List<LucroSemana> listaLucroSemana = new ArrayList<LucroSemana>();
    	
    	listaLucroSemana = o.consultarLucroSemana(con);
    	
    	if(totalMeses <= 0) {
    		
    		if(totalSemanas < 4) {
    			
    			return -100000.0;
    			
    		}else {
    			
    			while(i < 4) {
        			
        			lucroMes = lucroMes + listaLucroSemana.get(i).getValor();
        			i++;
    				
    			}
    			
    			return lucroMes;
    			
    		}
    		
    	}
    	else {
    		
    		int aux = totalSemanas/4;
    		int aux2 = (totalMeses*4);
    		
    		if(aux >= totalMeses + 1) {
    			
    			while(j < 4) {
    				
    				lucroMes = lucroMes + listaLucroSemana.get(aux2).getValor();
        			j++;
    				aux2++;
    			}
    			
    			return lucroMes;
    			
    		}
    		else {
    			return -100000.0;
    		}
    		
    	}
    	
    }

    @FXML
    void clicouFecharMes(ActionEvent event) {

    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	BigDecimal bd = new BigDecimal(calculoMes).setScale(2, RoundingMode.HALF_EVEN);
		
    	calculoMes = bd.doubleValue();
    	
    	o.inserirLucroMes(calculoMes, con);
		
		atualizarTabelaLucroMes();
    	
    }
    
    private double verificarFechamentoSemana() {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	int totalSemanas = o.totalSemanas(con);
    	int totalDias = o.totalDias(con);
    	int i = 0;
    	int j = 0;
    	
    	double lucroSemana = 0;
    	
    	List<Lucro> listaLucro = new ArrayList<Lucro>();
    	
    	listaLucro = o.consultarLucros(con);
    	
    	if(totalSemanas <= 0) {
    		
    		if(totalDias < 7) {
    			
    			return -100000.0;
    			
    		}else {
    			
    			while(i < 7) {
        			
        			lucroSemana = lucroSemana + listaLucro.get(i).getLucro();
        			i++;
    				
    			}
    			return lucroSemana;
    		}
    		
    	}
    	else {
    		
    		int aux = totalDias/7;
    		int aux2 = (totalSemanas*7);
    		
    		if(aux >= totalSemanas + 1) {
    			
    			while(j < 7) {
    				
    				lucroSemana = lucroSemana + listaLucro.get(aux2).getLucro();
        			j++;
    				aux2++;
    			}
    			
    			
    			return lucroSemana;
    			
    		}
    		else {
    			return -100000.0;
    		}
    		
    	}
    	
    }

    @FXML
    void clicouFecharSemana(ActionEvent event) {
    	
    	Lancamentos o = new Lancamentos();
    	Conexao con = new Conexao();
    	
    	BigDecimal bd = new BigDecimal(calculoSemana).setScale(2, RoundingMode.HALF_EVEN);
		
    	calculoSemana = bd.doubleValue();
    	
    	o.inserirLucroSemana(calculoSemana, con);
		
		atualizarTabelaLucroSemana();
 
    }
    
    @FXML
    void pressionouTeclaLucroDia(KeyEvent event) throws ParseException {

    	Conexao con = new Conexao();
    	Lancamentos o = new Lancamentos();
    	
    	String dataGet = tableLucro.getSelectionModel().getSelectedItem().getDia();
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(dataGet).getTime());
    	
    	if(event.getCode() == KeyCode.DELETE) {
    		
    		o.removerLucroDia(date, con);
    		
    		atualizarTabelaLucroDia();
    		
    		btnFechar.setDisable(false);
    		
    	}else {
    		
    	}
    	
    }
    
    @FXML
    void clicouVoltar(ActionEvent event) {
    	try {
			new ProximaTela("Inicial.fxml").start(Rodar.stage);
		} catch (Exception err) {
			err.printStackTrace();
		}
    }

}
