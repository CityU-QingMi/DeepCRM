    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        if (key == null) {
            // if there is no additional options, we output every single Key/Value pair for the MDC
            toAppendTo.append('{');
            event.getContextData().forEach(APPEND_EACH, toAppendTo);
            toAppendTo.append('}');
        } else {
            // otherwise they just want a single key output
            final Object val = event.getContextData().getValue(key);
            if (val != null) {
                toAppendTo.append(val);
            }
        }
    }
