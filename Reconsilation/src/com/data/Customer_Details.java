package com.data;

import com.opencsv.bean.CsvBindByPosition;

public class Customer_Details {
	
	@CsvBindByPosition(position = 0)
	private String Customer_Name;

	@CsvBindByPosition(position = 1)
	private String Refund_Issued;

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public String getRefund_Issued() {
		return Refund_Issued;
	}

}
