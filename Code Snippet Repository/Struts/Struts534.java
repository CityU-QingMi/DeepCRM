    protected void validateValue(Object object, Object value) {
        String stringValue = Objects.toString(value, "");

        if (StringUtils.isEmpty(stringValue)) {
            LOG.debug("Value is empty, use a required validator");
            return;
        }

        if (isTrim()) {
            stringValue = stringValue.trim();
            if (StringUtils.isEmpty(stringValue)) {
                LOG.debug("Value is empty, use a required validator");
                return;
            }
        }

        int minLengthToUse = getMinLength();
        int maxLengthToUse = getMaxLength();

        try {
            setCurrentValue(stringValue);
            if ((minLengthToUse > -1) && (stringValue.length() < minLengthToUse)) {
                addFieldError(fieldName, object);
            } else if ((maxLengthToUse > -1) && (stringValue.length() > maxLengthToUse)) {
                addFieldError(fieldName, object);
            }
        } finally {
            setCurrentValue(null);
        }
    }
