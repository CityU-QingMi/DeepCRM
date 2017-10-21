    public void validate(Object object) throws ValidationException {
        Object value = getFieldValue(fieldName, object);

        String stringValue = Objects.toString(value, EMPTY_STRING).trim();
        if (stringValue.length() == 0) {
            LOG.debug("Value for field {} is empty, won't ba validated, please use a required validator", fieldName);
            return;
        }

        if (value.getClass().isArray()) {
            Object[] values = (Object[]) value;
            for (Object objValue : values) {
                LOG.debug("Validating element of array: {}", objValue);
                validateValue(object, objValue);
            }
        } else if (Collection.class.isAssignableFrom(value.getClass())) {
            Collection values = (Collection) value;
            for (Object objValue : values) {
                LOG.debug("Validating element of collection: {}", objValue);
                validateValue(object, objValue);
            }
        } else {
            LOG.debug("Validating field: {}", value);
            validateValue(object, value);
        }
    }
