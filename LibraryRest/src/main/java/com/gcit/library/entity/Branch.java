package com.gcit.library.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Branch implements Serializable{
	
	private static final long serialVersionUID = -1111662428563760456L;
	
	private Integer branchNo;
	private String branchName;
	private String branchAddress;
	private List<Loan> loans;
	@JsonDeserialize(keyUsing=BookDeserializer.class)
	private Map<Book, Integer> stock;
	/**
	 * @return the branchNo
	 */
	public Integer getBranchNo() {
		return branchNo;
	}
	/**
	 * @param branchNo the branchNo to set
	 */
	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}
	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		return loans;
	}
	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	/**
	 * @return the stock
	 */
	public Map<Book, Integer> getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Map<Book, Integer> stock) {
		this.stock = stock;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((branchNo == null) ? 0 : branchNo.hashCode());
		result = prime * result + ((loans == null) ? 0 : loans.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (branchNo == null) {
			if (other.branchNo != null)
				return false;
		} else if (!branchNo.equals(other.branchNo))
			return false;
		if (loans == null) {
			if (other.loans != null)
				return false;
		} else if (!loans.equals(other.loans))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	
	
}

class BookDeserializer extends KeyDeserializer{

	@Override
	public Book deserializeKey(String arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		Book book = new Book();
		book.setBookId(Integer.parseInt(arg0));
		return null;
	}
	
}
