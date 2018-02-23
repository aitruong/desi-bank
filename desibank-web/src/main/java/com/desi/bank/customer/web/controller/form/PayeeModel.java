package com.desi.bank.customer.web.controller.form;

public class PayeeModel {

		private int id;
		private String payeeAccountNo;
		private String payeeName;
		private String payeeNickName;
		private String customerId;
		
		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPayeeAccountNo() {
			return payeeAccountNo;
		}

		public void setPayeeAccountNo(String payeeAccountNo) {
			this.payeeAccountNo = payeeAccountNo;
		}

		public String getPayeeName() {
			return payeeName;
		}

		public void setPayeeName(String payeeName) {
			this.payeeName = payeeName;
		}

		public String getPayeeNickName() {
			return payeeNickName;
		}

		public void setPayeeNickName(String payeeNickName) {
			this.payeeNickName = payeeNickName;
		}

		@Override
		public String toString() {
			return "PayeeInfo [id=" + id + ", payeeAccountNo=" + payeeAccountNo
					+ ", payeeName=" + payeeName + ", payeeNickName="
					+ payeeNickName + ", customerId=" + customerId + "]";
		}

		

	}


