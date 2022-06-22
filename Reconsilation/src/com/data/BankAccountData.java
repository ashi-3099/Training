package com.data;

import com.opencsv.bean.CsvBindByPosition;

public class BankAccountData {

	@CsvBindByPosition(position = 0)
	public String Customer_Name;

	@CsvBindByPosition(position = 1)
	public String Refund_Credited;
	
	public String getRefund() {
		return Refund_Credited;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

}
