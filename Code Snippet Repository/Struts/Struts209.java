    protected String convertToString(Locale locale, Object value) {
        if (Number.class.isInstance(value)) {
            NumberFormat format = NumberFormat.getNumberInstance(locale);
            format.setGroupingUsed(false);
            if (Double.class.isInstance(value) || BigDecimal.class.isInstance(value)) {
                format.setMinimumFractionDigits(1);
            }
            return format.format(value);
        } else {
            return Objects.toString(value, null);
        }
    }
