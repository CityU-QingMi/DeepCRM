    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        Object value = findListValue();

        if (headerKey != null) {
            addParameter("headerKey", findString(headerKey));
        }
        if (headerValue != null) {
            addParameter("headerValue", findString(headerValue));
        }
        if (emptyOption != null) {
            addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }

        if (value != null) {
            if (value instanceof Collection) {
                Collection tmp = (Collection) value;
                addParameter("list", tmp);
                if (listKey != null) {
                    addParameter("listKey", listKey);
                }
                if (listValue != null) {
                    addParameter("listValue", listValue);
                }
            } else if (value instanceof Map) {
                Map tmp = (Map) value;
                addParameter("list", MakeIterator.convert(tmp));
                addParameter("listKey", "key");
                addParameter("listValue", "value");                
            } else { // also covers "if (value.getClass().isArray())"
                Iterator i = MakeIterator.convert(value);
                addParameter("list", i);
                if (listKey != null) {
                    addParameter("listKey", listKey);
                }
                if (listValue != null) {
                    addParameter("listValue", listValue);
                }
            }
        }
    }
