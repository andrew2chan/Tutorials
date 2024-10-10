package com.consumer.soap_consumer;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.tempuri.Add;
import org.tempuri.AddResponse;

public class CalculatorClient extends WebServiceGatewaySupport {
    public AddResponse getAddition(int a, int b) {
        String uri = "http://www.dneonline.com/calculator.asmx";

        Add addRequest = new Add();
        addRequest.setIntA(a);
        addRequest.setIntB(b);

        AddResponse response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(uri, addRequest, new SoapActionCallback("http://tempuri.org/Add"));

        return response;
    }
}
