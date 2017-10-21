    protected Object convertToBigDecimal(Map<String, Object> context, String stringValue) {
        Locale locale = getLocale(context);

        NumberFormat format = getNumberFormat(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
            char separator = ((DecimalFormat) format).getDecimalFormatSymbols().getGroupingSeparator();
            stringValue = normalize(stringValue, separator);
        }

        LOG.debug("Trying to convert a value {} with locale {} to BigDecimal", stringValue, locale);
        ParsePosition parsePosition = new ParsePosition(0);
        Number number = format.parse(stringValue, parsePosition);

        if (parsePosition.getIndex() != stringValue.length()) {
            throw new XWorkException("Unparseable number: \"" + stringValue + "\" at position " + parsePosition.getIndex());
        }

        return number;
    }
