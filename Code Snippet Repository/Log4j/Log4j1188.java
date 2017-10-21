    @Override
    public StackTraceElement deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        // Must get an Object
        if (t == JsonToken.START_OBJECT) {
            String className = null, methodName = null, fileName = null;
            int lineNumber = -1;

            while ((t = jp.nextValue()) != JsonToken.END_OBJECT) {
                final String propName = jp.getCurrentName();
                if ("class".equals(propName)) {
                    className = jp.getText();
                } else if ("file".equals(propName)) {
                    fileName = jp.getText();
                } else if ("line".equals(propName)) {
                    if (t.isNumeric()) {
                        lineNumber = jp.getIntValue();
                    } else {
                        // An XML number always comes in a string since there is no syntax help as with JSON.
                        try {
                            lineNumber = Integer.parseInt(jp.getText().trim());
                        } catch (final NumberFormatException e) {
                            throw JsonMappingException.from(jp, "Non-numeric token (" + t + ") for property 'line'", e);
                        }
                    }
                } else if ("method".equals(propName)) {
                    methodName = jp.getText();
                } else if ("nativeMethod".equals(propName)) {
                    // no setter, not passed via constructor: ignore
                } else {
                    this.handleUnknownProperty(jp, ctxt, this._valueClass, propName);
                }
            }
            return new StackTraceElement(className, methodName, fileName, lineNumber);
        }
        throw ctxt.mappingException(this._valueClass, t);
    }
