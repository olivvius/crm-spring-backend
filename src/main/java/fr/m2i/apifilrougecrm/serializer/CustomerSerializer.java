package fr.m2i.apifilrougecrm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.m2i.apifilrougecrm.entity.Client;
import java.io.IOException;

public class CustomerSerializer extends StdSerializer<Client> {

    public CustomerSerializer() {
        this(null);
    }

    public CustomerSerializer(Class<Client> customer) {
        super(customer);
    }

    @Override
    public void serialize(Client customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", customer.getId());
        jsonGenerator.writeStringField("companyName", customer.getCompanyName());
        jsonGenerator.writeStringField("firstName", customer.getFirstName());
        jsonGenerator.writeStringField("lastName", customer.getLastName());
        jsonGenerator.writeStringField("email", customer.getEmail());
        jsonGenerator.writeStringField("phone", customer.getPhone());
        jsonGenerator.writeStringField("address", customer.getAddress());
        jsonGenerator.writeStringField("zipCode", customer.getZipCode());
        jsonGenerator.writeStringField("city", customer.getCity());
        jsonGenerator.writeStringField("country", customer.getCountry());
        jsonGenerator.writeNumberField("status", customer.getState());
        jsonGenerator.writeEndObject();
    }
}
