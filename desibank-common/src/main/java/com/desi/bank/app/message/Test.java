package com.desi.bank.app.message;

class Super {
	static String ID="QB";
	static{
		System.out.println("dx");
	}
}
class Sub extends Super{
	static{
		System.out.println("ZZZZZZZZZZZZZZZZZZZZZZ");
	}
}

public class Test {

	public static void main(String[] args) {
		try {
			Class.forName("com.desi.bank.app.message.Sub");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
