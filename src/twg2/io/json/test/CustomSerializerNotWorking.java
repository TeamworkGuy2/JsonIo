package twg2.io.json.test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import twg2.io.json.JsonBuilder;
import twg2.io.json.JsonInst;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
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
public class CustomSerializerNotWorking {

	public static class Widget {
		public int id;
		public Path path;

		public Widget() {
		}

		public Widget(int id, Path path) {
			this.id = id;
			this.path = path;
		}

		@Override public String toString() {
			return "{\"id\":" + id + ",\"path\":\"" + path.toString().replace("\\", "\\\\") + "\"}";
		}
	}


	public static class PathSerializer extends JsonSerializer<Path> {
		@Override public void serialize(Path path, JsonGenerator gen, SerializerProvider serializer) throws IOException {
			gen.writeString(path.toString());
		}
	}


	public static class PathDeserializer extends JsonDeserializer<Path> {
		@Override public Path deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			return Paths.get(parser.getText());
		}
	}


	@Test
	public void jacksonPathSerializerTest() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule customTypeHandlers = new SimpleModule();
		customTypeHandlers.addSerializer(Path.class, new PathSerializer());
		customTypeHandlers.addDeserializer(Path.class, new PathDeserializer());

		mapper.registerModule(customTypeHandlers);

		Widget[] objs = { new Widget(1, Paths.get("./rsc/text.txt")), new Widget(23, new File("other.dat").toPath()) };
		StringWriter out = new StringWriter();
		mapper.writeValue(out, objs);
		Widget[] resObjs = mapper.readValue(out.toString(), Widget[].class);

		String initial = Arrays.toString(objs).replace(", ", ",");
		String asJson = out.toString();
		String parsed = Arrays.toString(resObjs).replace(", ", ",");

		Assert.assertEquals("initial objects equal JSON", initial, asJson);
		Assert.assertEquals("initial objects equal parsed", initial, parsed);
	}


	@Test
	public void customPathSerializerTest() throws IOException {
		JsonBuilder jsonBldr = new JsonBuilder();
		jsonBldr.addSerializeDeserializer(Path.class, new PathSerializer(), new PathDeserializer());
		JsonInst json = jsonBldr.build();

		Widget[] objs = { new Widget(1, Paths.get("./rsc/text.txt")), new Widget(23, new File("other.dat").toPath()) };
		StringWriter out = new StringWriter();
		json.stringify(objs, out);
		Widget[] resObjs = json.parse(out.toString(), Widget[].class);

		String initial = Arrays.toString(objs).replace(", ", ",");
		String asJson = out.toString();
		String parsed = Arrays.toString(resObjs).replace(", ", ",");

		Assert.assertEquals("initial objects equal JSON", initial, asJson);
		Assert.assertEquals("initial objects equal parsed", initial, parsed);
	}

}
