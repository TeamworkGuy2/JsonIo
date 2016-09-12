JsonIo
==============
version: 0.1.2

Simple static and instance classes for easy Jackson (https://github.com/FasterXML) serialization/deserialization.  Includes ObjectMapper wrapper 'JsonInst' and ObjectMapper builder 'JsonBuilder'.

--------
JSON Instance:
```Java
public static JsonInst getJsonInst() {
	JsonBuilder jsonBldr = new JsonBuilder();

	jsonBldr.addSerializeDeserializer(Path.class, new Json.PathSerializer(), new Json.PathDeserializer());
	jsonBldr.addSerializeDeserializer(ZonedDateTime.class, Json.ZonedDateTimeSerializer.iso8601Formatter(), Json.ZonedDateTimeDeserializer.iso8601Formatter());
	jsonBldr.getSerializeDeserializeModule().addAbstractTypeMapping(Entry.class, AbstractMap.SimpleImmutableEntry.class);

	JsonInst json = jsonBldr.build();
	json.setAllowUnquotedFieldNames(true);
	json.setPrettyPrint(true);
	return json;
}
```
