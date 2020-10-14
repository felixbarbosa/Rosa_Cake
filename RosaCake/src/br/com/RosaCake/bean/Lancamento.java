package br.com.RosaCake.bean;

public class Lancamento {

	private String tipo;
	private String descricao;
	private double valor;
	private String data;
	private String pagamento;
	private String parcelas;
	
	
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public String getParcelas() {
		return parcelas;
	}
	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
