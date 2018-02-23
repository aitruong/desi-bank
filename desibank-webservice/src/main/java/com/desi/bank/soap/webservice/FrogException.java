package com.desi.bank.soap.webservice;

public class FrogException  extends  Exception{
		private MessageVO messageVO;
		
		/**
		 * 
		 * @param message
		 *  This is very high level description about the exception
		 * @param messageVO
		 *  This detailed description about the exception
		 */
		public FrogException(String message, MessageVO messageVO) {
			super(message);
			this.messageVO = messageVO;
		}
		
		public MessageVO getFault(){
			return messageVO;
		}
		
}
