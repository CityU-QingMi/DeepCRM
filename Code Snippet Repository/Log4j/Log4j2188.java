        @Override
        public StackTraceElement deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
                JsonProcessingException {
            JsonToken t = jp.getCurrentToken();
            // Must get an Object
            if (t == JsonToken.START_OBJECT) {
                String className = Strings.EMPTY, methodName = Strings.EMPTY, fileName = Strings.EMPTY;
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
                            throw JsonMappingException.from(jp, "Non-numeric token (" + t
                                    + ") for property 'lineNumber'");
                        }
                    } else if ("method".equals(propName)) {
                        methodName = jp.getText();
                    } else if ("nativeMethod".equals(propName)) {
                        // no setter, not passed via constructor: ignore
                    } else {
                        handleUnknownProperty(jp, ctxt, _valueClass, propName);
                    }
                }
                return new StackTraceElement(className, methodName, fileName, lineNumber);
            }
            throw ctxt.mappingException(_valueClass, t);
        }
