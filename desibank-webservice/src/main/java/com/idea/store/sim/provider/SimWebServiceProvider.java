package com.idea.store.sim.provider;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.idea.store.sim.DetailMessage;
import com.idea.store.sim.Sim;
import com.idea.store.sim.SimFaultMessageException;
import com.idea.store.sim.SimList;
import com.idea.store.sim.SimWebservice;
import com.idea.store.sim.Void;


@WebService(name="SimWebservice",endpointInterface="com.idea.store.sim.SimWebservice",portName="SimWebservicePort",serviceName="SimWebserviceService",targetNamespace="http://www.store.idea.com/sim",wsdlLocation="WEB-INF/wsdl/sim-webservice.wsdl")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class SimWebServiceProvider implements SimWebservice {

	@Override
	public Sim findSimById(String psim)  throws SimFaultMessageException {
		if("100".equals(psim)){
			DetailMessage faultInfo=new DetailMessage();
			faultInfo.setEmessage("Hey this psid 100 is not a valid  psid .. please try another one!");
			SimFaultMessageException exception=new SimFaultMessageException("Error at server side processing.", faultInfo);
			throw exception;		
		}
		Sim sim = new Sim();
		sim.setDescription("324432");
		sim.setName("Idea");
		sim.setPrice(333.4F);
		sim.setSid(psim);
		sim.setVendor("Idea Store");
		return sim;
	}

	@Override
	public SimList findAllSims(Void psim) {
		return null;
	}

}
