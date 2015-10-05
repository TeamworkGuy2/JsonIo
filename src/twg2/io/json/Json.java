package twg2.io.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/** Static JSON {@link ObjectMapper} wrapper
 * @author TeamworkGuy2
 * @since 2015-9-8
 */
public final class Json {
	private static final ObjectMapper defaultObjMapper;
	private static final JsonInst defaultInst;

	static {
		defaultObjMapper = new ObjectMapper();
		defaultObjMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		defaultInst = new JsonInst(defaultObjMapper);
	}


	public static JsonInst getDefaultInst() {
		return defaultInst;
	}


	public static ObjectMapper getDefaultObjectMapper() {
		return defaultObjMapper;
	}


	// ==== static defaultInst helpers ====
	// ==== Write methods ====
	public static <T> T parse(String src, Class<T> clazz) {
		T value = null;
		try {
			value = defaultObjMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public static <T> T parse(File src, Class<T> clazz) {
		T value = null;
		try {
			value = defaultObjMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public static <T> T parse(InputStream src, Class<T> clazz) {
		T value = null;
		try {
			value = defaultObjMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public static <T> T parse(Reader src, Class<T> clazz) {
		T value = null;
		try {
			value = defaultObjMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public static <T> T parse(URL src, Class<T> clazz) {
		T value = null;
		try {
			value = defaultObjMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	// ==== Write methods ====
	public static <T> String stringify(T src) {
		String value = null;
		try {
			value = defaultObjMapper.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public static <T> void stringify(T src, Appendable dst) {
		try {
			String value = defaultObjMapper.writeValueAsString(src);
			dst.append(value);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static <T> void stringify(T src, OutputStream dst) {
		try {
			defaultObjMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static <T> void stringify(T src, Writer dst) {
		try {
			defaultObjMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public static <T> void stringify(T src, File dst) {
		try {
			defaultObjMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}




	public static class ToStringSerializer extends JsonSerializer<Object> {

		@Override
		public void serialize(Object obj, JsonGenerator gen, SerializerProvider serializer) throws IOException, JsonProcessingException {
			gen.writeString(obj.toString());
		}

	}




	public static class PathSerializer extends JsonSerializer<Path> {

		@Override
		public void serialize(Path path, JsonGenerator gen, SerializerProvider serializer) throws IOException, JsonProcessingException {
			gen.writeString(path.toString());
		}

	}


	public static class PathDeserializer extends JsonDeserializer<Path> {

		@Override
		public Path deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
			return Paths.get(parser.getText());
		}

	}




	public static class FileSerializer extends JsonSerializer<File> {

		@Override
		public void serialize(File file, JsonGenerator gen, SerializerProvider serializer) throws IOException, JsonProcessingException {
			gen.writeString(file.toString());
		}

	}


	public static class FileDeserializer extends JsonDeserializer<File> {

		@Override
		public File deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
			return new File(parser.getText());
		}

	}




	public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
		private DateTimeFormatter formatter;


		public ZonedDateTimeSerializer(DateTimeFormatter formatter) {
			this.formatter = formatter;
		}


		@Override
		public void serialize(ZonedDateTime time, JsonGenerator gen, SerializerProvider serializer) throws IOException, JsonProcessingException {
			gen.writeString(time.format(formatter));
		}


		public static ZonedDateTimeSerializer iso8601Formatter() {
			return new ZonedDateTimeSerializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}

	}


	public static class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
		private DateTimeFormatter formatter;


		public ZonedDateTimeDeserializer(DateTimeFormatter formatter) {
			this.formatter = formatter;
		}


		@Override
		public ZonedDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
			return ZonedDateTime.parse(parser.getText(), formatter);
		}


		public static ZonedDateTimeDeserializer iso8601Formatter() {
			return new ZonedDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}

	}

}
