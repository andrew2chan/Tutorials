package com.consumer.soap_consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tempuri.AddResponse;

@SpringBootApplication
public class SoapConsumerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(SoapConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CalculatorClient calculatorClient = new CalculatorClient();

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

		marshaller.setContextPath("org.tempuri");

		calculatorClient.setMarshaller(marshaller);
		calculatorClient.setUnmarshaller(marshaller);

		AddResponse response = calculatorClient.getAddition(1, 2);

		System.out.println(response.getAddResult());
	}
}
