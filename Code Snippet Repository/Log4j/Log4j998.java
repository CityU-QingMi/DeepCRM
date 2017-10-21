    private static Type getTypeConverterSupportedType(@SuppressWarnings("rawtypes") final Class<? extends TypeConverter> typeConverterClass) {
        for (final Type type : typeConverterClass.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                final ParameterizedType pType = (ParameterizedType) type;
                if (TypeConverter.class.equals(pType.getRawType())) {
                    // TypeConverter<T> has only one type argument (T), so return that
                    return pType.getActualTypeArguments()[0];
                }
            }
        }
        return Void.TYPE;
    }
