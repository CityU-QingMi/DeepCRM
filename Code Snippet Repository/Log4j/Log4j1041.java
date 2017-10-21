    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
            final StringBuilder log) {
        final String name = this.annotation.value();
        final String elementValue = node.getValue();
        final String attributeValue = node.getAttributes().get("value");
        String rawValue = null; // if neither is specified, return null (LOG4J2-1313)
        if (Strings.isNotEmpty(elementValue)) {
            if (Strings.isNotEmpty(attributeValue)) {
                LOGGER.error("Configuration contains {} with both attribute value ({}) AND element" +
                                " value ({}). Please specify only one value. Using the element value.",
                        node.getName(), attributeValue, elementValue);
            }
            rawValue = elementValue;
        } else {
            rawValue = removeAttributeValue(node.getAttributes(), "value");
        }
        final String value = this.substitutor.replace(event, rawValue);
        StringBuilders.appendKeyDqValue(log, name, value);
        return value;
    }
