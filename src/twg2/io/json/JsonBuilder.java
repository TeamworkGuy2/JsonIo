package twg2.io.json;

import java.io.IOException;
import java.util.function.BiFunction;

import twg2.functions.TriConsumer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author TeamworkGuy2
 * @since 2015-9-26
 */
public class JsonBuilder {
	private SimpleModule customModule;


	public JsonInst build() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(this.customModule);
		return new JsonInst(mapper);
	}


	public SimpleModule getSerializeDeserializeModule() {
		if(this.customModule == null) {
			this.customModule = new SimpleModule();
		}
		return this.customModule;
	}


	public <T> void addSerializeDeserializer(Class<T> type, TriConsumer<T, JsonGenerator, SerializerProvider> serializer, BiFunction<JsonParser, DeserializationContext, T> deserializer) {
		SimpleModule module = getSerializeDeserializeModule();
		module.addSerializer(type, new JsonSerializer<T>() {
			@Override
			public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
				serializer.accept(value, gen, provider);
			}
		});

		module.addDeserializer(type, new JsonDeserializer<T>() {
			@Override
			public T deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
				return deserializer.apply(parser, context);
			}
		});
	}


	public <T> void addSerializeDeserializer(Class<T> type, JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
		SimpleModule module = getSerializeDeserializeModule();
		module.addSerializer(type, serializer);
		module.addDeserializer(type, deserializer);
	}

}
