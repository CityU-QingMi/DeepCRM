    public Object convertValue(Map<String, Object> context, Object target, Member member, String propertyName, Object value, Class toType) {
        Collection result;
        Class memberType = String.class;

        if (target != null) {
            memberType = objectTypeDeterminer.getElementClass(target.getClass(), propertyName, null);

            if (memberType == null) {
                memberType = String.class;
            }
        }

        if (toType.isAssignableFrom(value.getClass())) {
            // no need to do anything
            result = (Collection) value;
        } else if (value.getClass().isArray()) {
            Object[] objArray = (Object[]) value;
            TypeConverter converter = getTypeConverter(context);
            result = createCollection(toType, memberType, objArray.length);

            for (Object anObjArray : objArray) {
                Object convertedValue = converter.convertValue(context, target, member, propertyName, anObjArray, memberType);
                if (!TypeConverter.NO_CONVERSION_POSSIBLE.equals(convertedValue)) {
                    result.add(convertedValue);
                }
            }
        } else if (Collection.class.isAssignableFrom(value.getClass())) {
            Collection col = (Collection) value;
            TypeConverter converter = getTypeConverter(context);
            result = createCollection(toType, memberType, col.size());

            for (Object aCol : col) {
                Object convertedValue = converter.convertValue(context, target, member, propertyName, aCol, memberType);
                if (!TypeConverter.NO_CONVERSION_POSSIBLE.equals(convertedValue)) {
                    result.add(convertedValue);
                }
            }
        } else {
            result = createCollection(toType, memberType, -1);
            TypeConverter converter = getTypeConverter(context);
            Object convertedValue = converter.convertValue(context, target, member, propertyName, value, memberType);
            if (!TypeConverter.NO_CONVERSION_POSSIBLE.equals(convertedValue)) {
                result.add(convertedValue);
            }
        }

        return result;
    }
