    public final static Number toFloat(final String value) {
        try {
            if (Double.parseDouble(value) > Double.MAX_VALUE) {
                return new BigDecimal(value);
            } else {
                return new Double(value);
            }
        } catch (NumberFormatException e0) {
            return new BigDecimal(value);
        }
    }
