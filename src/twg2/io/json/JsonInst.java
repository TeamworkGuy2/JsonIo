package twg2.io.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/** JSON {@link ObjectMapper} instance wrapper
 * @author TeamworkGuy2
 * @since 2015-9-9
 */
public final class JsonInst {
	private final ObjectMapper objMapper;


	public JsonInst() {
		this.objMapper = new ObjectMapper();
	}


	public JsonInst(ObjectMapper mapper) {
		this.objMapper = mapper;
	}


	public ObjectMapper getObjectMapper() {
		return this.objMapper;
	}


	public void setPrettyPrint(boolean doPrettyPrint) {
		if(doPrettyPrint) {
			this.objMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		else {
			this.objMapper.disable(SerializationFeature.INDENT_OUTPUT);
		}
	}


	public void setAllowUnquotedFieldNames(boolean allowUnquotedFieldNames) {
		this.objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, allowUnquotedFieldNames);
	}


	public void setFieldVisibilityAll() {
		Json.getDefaultObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}


	public void setFieldVisibility(Visibility visibility) {
		Json.getDefaultObjectMapper().setVisibility(PropertyAccessor.FIELD, visibility);
	}


	public <T> T parse(String src, Class<T> clazz) {
		T value = null;
		try {
			value = objMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public <T> T parse(File src, Class<T> clazz) {
		T value = null;
		try {
			value = objMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public <T> T parse(InputStream src, Class<T> clazz) {
		T value = null;
		try {
			value = objMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public <T> T parse(Reader src, Class<T> clazz) {
		T value = null;
		try {
			value = objMapper.readValue(src, clazz);
		} catch (JsonParseException e) {
			throw new UncheckedIOException(e);
		} catch (JsonMappingException e) {
			throw new UncheckedIOException(e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public <T> T parse(URL src, Class<T> clazz) {
		T value = null;
		try {
			value = objMapper.readValue(src, clazz);
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
	public <T> String stringify(T src) {
		String value = null;
		try {
			value = objMapper.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			throw new UncheckedIOException(e);
		}
		return value;
	}


	public <T> void stringify(T src, Appendable dst) {
		try {
			String value = objMapper.writeValueAsString(src);
			dst.append(value);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public <T> void stringify(T src, OutputStream dst) {
		try {
			objMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public <T> void stringify(T src, Writer dst) {
		try {
			objMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}


	public <T> void stringify(T src, File dst) {
		try {
			objMapper.writeValue(dst, src);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
