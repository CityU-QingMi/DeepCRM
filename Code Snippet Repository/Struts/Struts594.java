    private void setNumberFormatParameters(NumberFormat format) {
        if (groupingUsed != null) {
            format.setGroupingUsed(groupingUsed);
        }
        if (maximumFractionDigits != null) {
            format.setMaximumFractionDigits(maximumFractionDigits);
        }
        if (maximumIntegerDigits != null) {
            format.setMaximumIntegerDigits(maximumIntegerDigits);
        }
        if (minimumFractionDigits != null) {
            format.setMinimumFractionDigits(minimumFractionDigits);
        }
        if (minimumIntegerDigits != null) {
            format.setMinimumIntegerDigits(minimumIntegerDigits);
        }
        if (parseIntegerOnly != null) {
            format.setParseIntegerOnly(parseIntegerOnly);
        }
    }
