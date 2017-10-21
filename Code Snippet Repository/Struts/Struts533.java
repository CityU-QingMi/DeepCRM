    public void validate(Object object) throws ValidationException {
        Object fieldValue = getFieldValue(fieldName, object);

        if (fieldValue == null) {
            LOG.debug("Value for field {} is null, use a required validator", getFieldName());
        } else if (fieldValue.getClass().isArray()) {
            Object[] values = (Object[]) fieldValue;
            for (Object value : values) {
                validateValue(object, value);
            }
        } else if (Collection.class.isAssignableFrom(fieldValue.getClass())) {
            Collection values = (Collection) fieldValue;
            for (Object value : values) {
                validateValue(object, value);
            }
        } else {
            validateValue(object, fieldValue);
        }
    }
