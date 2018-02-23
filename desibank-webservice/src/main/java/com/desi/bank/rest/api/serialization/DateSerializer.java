package com.desi.bank.rest.api.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author nagendra
 *
 */
public class DateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = dateFormat.format(dateFormat);
		jsonGenerator.writeString(dateString);
	}
}