    private static Type getConstraintValidatorAnnotationType(final Class<? extends ConstraintValidator<?>> type) {
        for (final Type parentType : type.getGenericInterfaces()) {
            if (parentType instanceof ParameterizedType) {
                final ParameterizedType parameterizedType = (ParameterizedType) parentType;
                if (ConstraintValidator.class.equals(parameterizedType.getRawType())) {
                    return parameterizedType.getActualTypeArguments()[0];
                }
            }
        }
        return Void.TYPE;
    }
