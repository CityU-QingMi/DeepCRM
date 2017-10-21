    protected void validateValue(Object obj, Double maxInclusiveToUse, Double minInclusiveToUse, Double maxExclusiveToUse, Double minExclusiveToUse) {
        try {
            setCurrentValue(obj);
            Double value = Double.valueOf(obj.toString());
            if ((maxInclusiveToUse != null && value.compareTo(maxInclusiveToUse) > 0) ||
                    (minInclusiveToUse != null && value.compareTo(minInclusiveToUse) < 0) ||
                    (maxExclusiveToUse != null && value.compareTo(maxExclusiveToUse) >= 0) ||
                    (minExclusiveToUse != null && value.compareTo(minExclusiveToUse) <= 0)) {
                addFieldError(getFieldName(), value);
            }
        } catch (NumberFormatException e) {
            LOG.debug("Cannot validate value {} - not a Double", e);
        } finally {
            setCurrentValue(null);
        }
    }
