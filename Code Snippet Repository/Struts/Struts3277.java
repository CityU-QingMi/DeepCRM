    @SuppressWarnings("")
    public Object convert(Class clazz, Type type, Object value, Method method)
            throws IllegalArgumentException, JSONException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchMethodException, IntrospectionException {

        if (value == null) {
            // if it is a java primitive then get a default value, otherwise
            // leave it as null
            return clazz.isPrimitive() ? convertPrimitive(clazz, value, method) : null;
        } else if (isJSONPrimitive(clazz))
            return convertPrimitive(clazz, value, method);
        else if (Collection.class.isAssignableFrom(clazz))
            return convertToCollection(clazz, type, value, method);
        else if (Map.class.isAssignableFrom(clazz))
            return convertToMap(clazz, type, value, method);
        else if (clazz.isArray())
            return convertToArray(clazz, type, value, method);
        else if (value instanceof Map) {
            // nested bean
            Object convertedValue = clazz.newInstance();
            this.populateObject(convertedValue, (Map) value);
            return convertedValue;
        } else if (BigDecimal.class.equals(clazz)) {
            return new BigDecimal(value.toString());
        } else if (BigInteger.class.equals(clazz)) {
            return new BigInteger(value.toString());
        } else
            throw new JSONException("Incompatible types for property " + method.getName());
    }
