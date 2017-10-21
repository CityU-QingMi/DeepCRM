    protected void validateFieldValue(Object object, String value, String regexToUse) {
        // string must not be empty
        String str = value.trim();
        if (str.length() == 0) {
            LOG.debug("Value is empty, please use a required validator");
            return;
        }

        // match against expression
        Pattern pattern;
        if (isCaseSensitive()) {
            pattern = Pattern.compile(regexToUse);
        } else {
            pattern = Pattern.compile(regexToUse, Pattern.CASE_INSENSITIVE);
        }

        String compare = value;
        if (isTrimed()) {
            compare = compare.trim();
        }

        try {
            setCurrentValue(compare);
            Matcher matcher = pattern.matcher(compare);
            if (!matcher.matches()) {
                addFieldError(fieldName, object);
            }
        } finally {
            setCurrentValue(null);
        }
    }
