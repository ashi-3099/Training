package com.main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.data.BankAccountData;
import com.data.Customer_Details;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
public class MatchChecker {
	
	public static void main(String[] args)
			throws IOException {
		List<BankAccountData> BankAccountData;
		List<Customer_Details> Customer_Details;

		try {
			//Reading BankAccountData.csv file using openCSV parser
			BankAccountData = new CsvToBeanBuilder<BankAccountData>(
					new FileReader("C:\\Users\\ASHAGRAW\\OneDrive - Capgemini\\Desktop\\Reconsilation\\Resources\\Bank Account.csv")).withType(BankAccountData.class)
							.withSkipLines(1).build().parse();

			//Reading CustomerDetails.csv file using openCSV parser
			Customer_Details= new CsvToBeanBuilder<Customer_Details>(
					new FileReader("C:\\Users\\ASHAGRAW\\OneDrive - Capgemini\\Desktop\\Reconsilation\\Resources\\Customer_Details.csv"))
							.withType(Customer_Details.class).withSkipLines(1).build().parse();

			//List of String Array 
			List<String[]> comparisonData = new ArrayList<String[]>();
			
			//writing matched and mismatched data in ComparisonData.csv
			Writer outputFile = new FileWriter("C:\\Users\\ASHAGRAW\\OneDrive - Capgemini\\Desktop\\Reconsilation\\ComparisonData.csv");
			CSVWriter writer = new CSVWriter(outputFile);
			String[] header = {  "Customer_Name" , "Refund_Issued_Company", "Refund_Credited_Bank", "AmountDeficit", "AmountSurplus"};
			comparisonData.add(header);
			
			//checking if the amount credited is deficit or surplus with respect to the actual amount
			for (int i = 0; i < Customer_Details.size(); i++) {
				int amountDeficit = 0;
				int amountSurplus = 0;
				if (Integer.parseInt(Customer_Details.get(i).getRefund_Issued()) > Integer.parseInt(BankAccountData.get(i).getRefund())) {
					amountDeficit = Integer.parseInt(Customer_Details.get(i).getRefund_Issued())
							- Integer.parseInt(BankAccountData.get(i).getRefund());
					amountSurplus = 0;
				} else if (Integer.parseInt(Customer_Details.get(i).getRefund_Issued()) < Integer
						.parseInt(BankAccountData.get(i).getRefund())) {
					amountSurplus = -Integer.parseInt(Customer_Details.get(i).getRefund_Issued())
							+ Integer.parseInt(BankAccountData.get(i).getRefund());
				amountDeficit = 0;
				}

				//adding the compared data to list of string array
				comparisonData.add(new String[] {  
						BankAccountData.get(i).getCustomer_Name(),
						Customer_Details.get(i).getRefund_Issued(), 
						BankAccountData.get(i).getRefund(), 
						Integer.toString(amountDeficit),
						Integer.toString(amountSurplus) 
						});

			}
			writer.writeAll(comparisonData);
			writer.close();

			System.out.println("Successfully created match vs mismatch data in Comparison.csv file. Refresh the Project file if not vissible.");

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}

	}
}
