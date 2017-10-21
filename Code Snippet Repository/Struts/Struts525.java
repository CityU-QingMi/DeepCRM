    public void validate(Object object) throws ValidationException {
        Object obj = getFieldValue(getFieldName(), object);

        // if there is no value - don't do comparison
        // if a value is required, a required validator should be added to the field
        if (obj == null) {
            return;
        }

        T min = getMin();
        T max = getMax();

        if (obj.getClass().isArray()) {
            Object[] values = (Object[]) obj;
            for (Object objValue : values) {
                validateValue(object, (Comparable<T>) objValue, min, max);
            }
        } else if (Collection.class.isAssignableFrom(obj.getClass())) {
            Collection<?> values = (Collection<?>) obj;
            for (Object objValue : values) {
                validateValue(object, (Comparable<T>) objValue, min, max);
            }
        } else {
            validateValue(object, (Comparable<T>) obj, min, max);
        }
    }
