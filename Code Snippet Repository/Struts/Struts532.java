    protected void validateValue(Object object, Object fieldValue) {
        try {
            setCurrentValue(fieldValue);
            if (fieldValue == null) {
                addFieldError(getFieldName(), object);
                return;
            }

            if (fieldValue instanceof String) {
                String stingValue = (String) fieldValue;

                if (trim) {
                    stingValue = stingValue.trim();
                }

                if (stingValue.length() == 0) {
                    addFieldError(getFieldName(), object);
                }
            } else {
                addFieldError(getFieldName(), object);
            }
        } finally {
            setCurrentValue(null);
        }
    }
