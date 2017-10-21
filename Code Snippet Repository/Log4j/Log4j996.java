    public TypeConverter<?> findCompatibleConverter(final Type type) {
        Objects.requireNonNull(type, "No type was provided");
        final TypeConverter<?> primary = registry.get(type);
        // cached type converters
        if (primary != null) {
            return primary;
        }
        // dynamic enum support
        if (type instanceof Class<?>) {
            final Class<?> clazz = (Class<?>) type;
            if (clazz.isEnum()) {
                @SuppressWarnings({"unchecked","rawtypes"})
                final EnumConverter<? extends Enum> converter = new EnumConverter(clazz.asSubclass(Enum.class));
                registry.putIfAbsent(type, converter);
                return converter;
            }
        }
        // look for compatible converters
        for (final Map.Entry<Type, TypeConverter<?>> entry : registry.entrySet()) {
            final Type key = entry.getKey();
            if (TypeUtil.isAssignable(type, key)) {
                LOGGER.debug("Found compatible TypeConverter<{}> for type [{}].", key, type);
                final TypeConverter<?> value = entry.getValue();
                registry.putIfAbsent(type, value);
                return value;
            }
        }
        throw new UnknownFormatConversionException(type.toString());
    }
