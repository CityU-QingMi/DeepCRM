    protected void validateValue(Object object, Comparable<T> value, T min, T max) {
        setCurrentValue(value);

        // only check for a minimum value if the min parameter is set
        if ((min != null) && (value.compareTo(min) < 0)) {
            addFieldError(getFieldName(), object);
        }

        // only check for a maximum value if the max parameter is set
        if ((max != null) && (value.compareTo(max) > 0)) {
            addFieldError(getFieldName(), object);
        }

        setCurrentValue(null);
    }
