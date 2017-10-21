    protected void validateValue(Object object, Object value) {
        String stringValue = Objects.toString(value, EMPTY_STRING).trim();
        if (stringValue.length() == 0) {
            LOG.debug("Value for field {} is empty, won't ba validated, please use a required validator", fieldName);
            return;
        }

        try {
            setCurrentValue(value);
            if (!(value.getClass().equals(String.class)) || !getUrlPattern().matcher(stringValue).matches()) {
                addFieldError(fieldName, object);
            }
        } finally {
            setCurrentValue(null);
        }
    }
