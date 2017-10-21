    public void validate(Object object) throws ValidationException {
        Object fieldValue = this.getFieldValue(getFieldName(), object);

        if (fieldValue == null) {
            addFieldError(getFieldName(), object);
            return;
        }

        if (fieldValue.getClass().isArray()) {
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
