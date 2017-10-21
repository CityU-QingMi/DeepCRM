    @SuppressWarnings("")
    private Object convertToArray(Class clazz, Type type, Object value, Method accessor)
            throws JSONException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchMethodException, IntrospectionException {
        if (value == null)
            return null;
        else if (value instanceof List) {
            Class arrayType = clazz.getComponentType();
            List values = (List) value;
            Object newArray = Array.newInstance(arrayType, values.size());

            // create an object for each element
            for (int j = 0; j < values.size(); j++) {
                Object listValue = values.get(j);

                if (arrayType.equals(Object.class)) {
                    // Object[]
                    Array.set(newArray, j, listValue);
                } else if (isJSONPrimitive(arrayType)) {
                    // primitive array
                    Array.set(newArray, j, this.convertPrimitive(arrayType, listValue, accessor));
                } else if (listValue instanceof Map) {
                    // array of other class
                    Object newObject;
                    if (Map.class.isAssignableFrom(arrayType)) {
                        newObject = convertToMap(arrayType, type, listValue, accessor);
                    } else if (List.class.isAssignableFrom(arrayType)) {
                        newObject = convertToCollection(arrayType, type, listValue, accessor);
                    } else {
                        newObject = arrayType.newInstance();
                        this.populateObject(newObject, (Map) listValue);
                    }

                    Array.set(newArray, j, newObject);
                } else
                    throw new JSONException("Incompatible types for property " + accessor.getName());
            }

            return newArray;
        } else
            throw new JSONException("Incompatible types for property " + accessor.getName());
    }
