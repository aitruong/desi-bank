package com.desi.bank.customer.web.controller;

import java.io.IOException;

public class OpenNotesPad {
		public static void main(String[] args) throws IOException {
			for(int x=0;x<5;x++) {
					//Using this we can execute all the OS command in java
					Runtime.getRuntime().exec("notepad");
			}
		}
}
