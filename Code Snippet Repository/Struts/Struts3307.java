    protected Object getBridgedValue(Method baseAccessor, Object value) throws InstantiationException, IllegalAccessException {
        JSONFieldBridge fieldBridgeAnn = baseAccessor.getAnnotation(JSONFieldBridge.class);
        if (fieldBridgeAnn != null) {
            Class impl = fieldBridgeAnn.impl();
            FieldBridge instance = (FieldBridge) impl.newInstance();

            if (fieldBridgeAnn.params().length > 0 && ParameterizedBridge.class.isAssignableFrom(impl)) {
                Map<String, String> params = new HashMap<>(fieldBridgeAnn.params().length);
                for (JSONParameter param : fieldBridgeAnn.params()) {
                    params.put(param.name(), param.value());
                }
                ((ParameterizedBridge) instance).setParameterValues(params);
            }
            value = instance.objectToString(value);
        }
        return value;
    }
