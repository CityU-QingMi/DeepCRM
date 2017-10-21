    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);

        if (value == null) {
            addFieldError(fieldName, object);
        } else if (value.getClass().isArray() && Array.getLength(value) == 0) {
            addFieldError(fieldName, object);
        } else if (Collection.class.isAssignableFrom(value.getClass()) && ((Collection) value).size() == 0) {
            addFieldError(fieldName, object);
        }
    }
