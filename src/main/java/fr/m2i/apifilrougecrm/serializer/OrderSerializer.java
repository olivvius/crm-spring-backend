package fr.m2i.apifilrougecrm.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.m2i.apifilrougecrm.entity.Order;

import java.io.IOException;

/**
 *
 * @author Sebastien Bissay
 */
public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(null);
    }

    public OrderSerializer(Class<Order> order) {
        super(order);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", order.getId());
        jsonGenerator.writeStringField("type", order.getTypePresta());
        jsonGenerator.writeStringField("designation", order.getDesignation());
        jsonGenerator.writeObjectFieldStart("client");
        {
            jsonGenerator.writeNumberField("id", order.getClient().getId());
            jsonGenerator.writeStringField("firstName", order.getClient().getFirstName());
            jsonGenerator.writeStringField("lastName", order.getClient().getLastName());
            jsonGenerator.writeStringField("company", order.getClient().getCompanyName());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeNumberField("numberOfDays", order.getNbDays());
        jsonGenerator.writeNumberField("unitPrice", order.getUnitPrice());
        jsonGenerator.writeNumberField("statut", order.getState());
        jsonGenerator.writeEndObject();
    }
}
