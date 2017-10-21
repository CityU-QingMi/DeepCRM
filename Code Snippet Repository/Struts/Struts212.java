    public static String getConversionErrorMessage(String propertyName, ValueStack stack) {
        LocalizedTextProvider localizedTextProvider = ActionContext.getContext().getContainer().getInstance(LocalizedTextProvider.class);
        String defaultMessage = localizedTextProvider.findDefaultText("xwork.default.invalid.fieldvalue",
                ActionContext.getContext().getLocale(),
                new Object[]{
                        propertyName
                });

        List<String> indexValues = getIndexValues(propertyName);

        propertyName = removeAllIndexesInPropertyName(propertyName);

        String getTextExpression = "getText('" + CONVERSION_ERROR_PROPERTY_PREFIX + propertyName + "','" + defaultMessage + "')";
        String message = (String) stack.findValue(getTextExpression);

        if (message == null) {
            message = defaultMessage;
        } else {
            message = MessageFormat.format(message, indexValues.toArray());
        }

        return message;
    }
