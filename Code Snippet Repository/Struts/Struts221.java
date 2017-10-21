    public void setRule(String rule) {
        if (rule != null && rule.length() > 0) {
            if (rule.equals(ConversionRule.COLLECTION.toString())) {
                this.rule = DefaultObjectTypeDeterminer.DEPRECATED_ELEMENT_PREFIX;
            } else if (rule.equals(ConversionRule.ELEMENT.toString())) {
                this.rule = DefaultObjectTypeDeterminer.ELEMENT_PREFIX;
            } else if (rule.equals(ConversionRule.KEY.toString())) {
                this.rule = DefaultObjectTypeDeterminer.KEY_PREFIX;
            } else if (rule.equals(ConversionRule.KEY_PROPERTY.toString())) {
                this.rule = DefaultObjectTypeDeterminer.KEY_PROPERTY_PREFIX;
            } else if (rule.equals(ConversionRule.MAP.toString())) {
                this.rule = MAP_PREFIX;
            }
        }
    }
