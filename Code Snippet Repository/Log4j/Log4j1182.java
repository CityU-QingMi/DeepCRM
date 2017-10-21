    @Override
    public StringMap deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
            JsonProcessingException {

        // Sanity check: verify that we got "Json Object":
//        JsonToken tok = jp.nextToken();
//        if (tok != JsonToken.START_OBJECT) {
//            throw new IOException("Expected data to start with an Object");
//        }
        final StringMap contextData = ContextDataFactory.createContextData();
        // Iterate over object fields:
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            final String fieldName = jp.getCurrentName();

            // move to value
            jp.nextToken();
            contextData.putValue(fieldName, jp.getText());
        }
        return contextData;
    }
