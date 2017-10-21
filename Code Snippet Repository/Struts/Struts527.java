    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);
        // if there is no value - don't do comparison
        // if a value is required, a required validator should be added to the field
        String regexToUse = getRegex();
        LOG.debug("Defined regexp as [{}]", regexToUse);

        if (value == null || regexToUse == null) {
            LOG.debug("Either value is empty (please use a required validator) or regex is empty");
            return;
        }

        if (value.getClass().isArray()) {
            Object[] values = (Object[]) value;
            for (Object objValue: values) {
                validateFieldValue(object, Objects.toString(objValue, EMPTY_STRING), regexToUse);
            }
        } else if (Collection.class.isAssignableFrom(value.getClass())) {
            Collection values = (Collection) value;
            for (Object objValue : values) {
                validateFieldValue(object, Objects.toString(objValue, EMPTY_STRING), regexToUse);
            }
        } else {
           validateFieldValue(object, Objects.toString(value, EMPTY_STRING), regexToUse);
        }
    }
