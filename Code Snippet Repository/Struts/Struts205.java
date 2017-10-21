    protected Object convertToDouble(Map<String, Object> context, String stringValue) {
        Locale locale = getLocale(context);

        NumberFormat format = getNumberFormat(locale);
        if (format instanceof DecimalFormat) {
            char separator = ((DecimalFormat) format).getDecimalFormatSymbols().getGroupingSeparator();
            stringValue = normalize(stringValue, separator);
        }

        LOG.debug("Trying to convert a value {} with locale {} to Double", stringValue, locale);
        ParsePosition parsePosition = new ParsePosition(0);
        Number number = format.parse(stringValue, parsePosition);

        if (parsePosition.getIndex() != stringValue.length()) {
            throw new XWorkException("Unparseable number: \"" + stringValue + "\" at position " + parsePosition.getIndex());
        }

        if (!isInRange(number, stringValue, Double.class)) {
            throw new XWorkException("Overflow or underflow converting: \"" + stringValue + "\" into class " + number.getClass().getName());
        }

        if (number != null) {
            return number.doubleValue();
        }

        return null;
    }
