    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
                        final StringBuilder log) {
        final String name = this.annotation.value();
        final Map<String, String> attributes = node.getAttributes();
        final String rawValue = removeAttributeValue(attributes, name, this.aliases);
        final String replacedValue = this.substitutor.replace(event, rawValue);
        final Object defaultValue = findDefaultValue(event);
        final Object value = convert(replacedValue, defaultValue);
        final Object debugValue = this.annotation.sensitive() ? NameUtil.md5(value + this.getClass().getName()) : value;
        StringBuilders.appendKeyDqValue(log, name, debugValue);
        return value;
    }
