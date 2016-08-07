package twg2.io.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

/** Utilities for reading specific node types from a {@link JsonNode} tree.
 * TODO this is not currently used, it was written for another project where it did not end up getting used, but it might be useful in future
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
public class JsonTreeExtractor {

	/** Return a textual JsonNode's text, if not a text node, throw an error. 
	 * @param node
	 * @return
	 */
	private static final String toString(JsonNode node) {
		if(!node.isTextual()) {
			throw new IllegalStateException("node is not text, expected text node");
		}
		return node.asText();
	}


	/** Return an array JsonNode's string array.
	 * If null, return null.
	 * If not an array, throw an error.
	 * If an element of the array is not textual, throw an error.
	 * @param node
	 * @return
	 */
	private static final List<String> toStringList(JsonNode node) {
		if(node != null) {
			if(node.getNodeType() != JsonNodeType.ARRAY) {
				throw new IllegalArgumentException("expected array node, received " + node.getNodeType());
			}

			List<String> list = new ArrayList<>();
			Iterator<JsonNode> fields = node.elements();
			while(fields.hasNext()) {
				JsonNode field = fields.next();
				if(!field.isTextual()) {
					throw new IllegalArgumentException("array element is not text, expected array of text values");
				}
				list.add(field.asText());
			}
			return list;
		}
		return null;
	}


	/** Return an object JsonNode's string map.
	 * If null, return null.
	 * If not an object, throw an error.
	 * If a field of the object is not textual, throw an error.
	 * @param node
	 * @return
	 */
	private static final Map<String, String> toStringMap(JsonNode node) {
		if(node != null) {
			if(node.getNodeType() != JsonNodeType.OBJECT) {
				throw new IllegalArgumentException("expected object node, received " + node.getNodeType());
			}

			Map<String, String> map = new HashMap<>();
			Iterator<Entry<String, JsonNode>> fields = node.fields();
			while(fields.hasNext()) {
				Entry<String, JsonNode> field = fields.next();
				if(!field.getValue().isTextual()) {
					throw new IllegalStateException("field '" + field.getKey() + "' is not text, expected map of text values");
				}
				map.put(field.getKey(), field.getValue().asText());
			}
			return map;
		}
		return null;
	}


	/** Return an object JsonNode's map.
	 * If null, return null.
	 * If not an object, throw an error.
	 * @param node
	 * @return
	 */
	private static final Map<String, JsonNode> toMap(JsonNode node) {
		if(node != null) {
			if(node.getNodeType() != JsonNodeType.OBJECT) {
				throw new IllegalArgumentException("expected object node, received " + node.getNodeType());
			}

			Map<String, JsonNode> map = new HashMap<>();
			Iterator<Entry<String, JsonNode>> fields = node.fields();
			while(fields.hasNext()) {
				Entry<String, JsonNode> field = fields.next();
				map.put(field.getKey(), field.getValue());
			}
			return map;
		}
		return null;
	}

}
