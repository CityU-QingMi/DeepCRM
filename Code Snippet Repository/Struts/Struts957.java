    protected void populateParams() {
        super.populateParams();
        Number n = (Number) component;
        n.setName(name);
        n.setCurrency(currency);
        n.setType(type);
        n.setGroupingUsed(groupingUsed);
        n.setMaximumFractionDigits(maximumFractionDigits);
        n.setMaximumIntegerDigits(maximumIntegerDigits);
        n.setMinimumFractionDigits(minimumFractionDigits);
        n.setMinimumIntegerDigits(minimumIntegerDigits);
        n.setParseIntegerOnly(parseIntegerOnly);
        n.setRoundingMode(roundingMode);

    }
