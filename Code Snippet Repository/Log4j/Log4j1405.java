    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final ReadOnlyStringMap contextData = event.getContextData();
        // if there is no additional options, we output every single
        // Key/Value pair for the MDC in a similar format to Hashtable.toString()
        if (full) {
            if (contextData == null || contextData.size() == 0) {
                toAppendTo.append("{}");
                return;
            }
            appendFully(contextData, toAppendTo);
        } else {
            if (keys != null) {
                if (contextData == null || contextData.size() == 0) {
                    toAppendTo.append("{}");
                    return;
                }
                appendSelectedKeys(keys, contextData, toAppendTo);
            } else if (contextData != null){
                // otherwise they just want a single key output
                final Object value = contextData.getValue(key);
                if (value != null) {
                    StringBuilders.appendValue(toAppendTo, value);
                }
            }
        }
    }
