package com.example.infraction.requests;

import java.math.BigDecimal;

public class PaymentRequest {
	
	private String nonce;
	
	private BigDecimal amount;
	
	private int quantity ;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
