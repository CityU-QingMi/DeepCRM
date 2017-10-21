    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final Message msg = event.getMessage();
        if (msg instanceof StringBuilderFormattable) {

            final boolean doRender = textRenderer != null;
            final StringBuilder workingBuilder = doRender ? new StringBuilder(80) : toAppendTo;

            final StringBuilderFormattable stringBuilderFormattable = (StringBuilderFormattable) msg;
            final int offset = workingBuilder.length();
            stringBuilderFormattable.formatTo(workingBuilder);

            // TODO can we optimize this?
            if (config != null && !noLookups) {
                for (int i = offset; i < workingBuilder.length() - 1; i++) {
                    if (workingBuilder.charAt(i) == '$' && workingBuilder.charAt(i + 1) == '{') {
                        final String value = workingBuilder.substring(offset, workingBuilder.length());
                        workingBuilder.setLength(offset);
                        workingBuilder.append(config.getStrSubstitutor().replace(event, value));
                    }
                }
            }
            if (doRender) {
                textRenderer.render(workingBuilder, toAppendTo);
            }
            return;
        }
        if (msg != null) {
            String result;
            if (msg instanceof MultiformatMessage) {
                result = ((MultiformatMessage) msg).getFormattedMessage(formats);
            } else {
                result = msg.getFormattedMessage();
            }
            if (result != null) {
                toAppendTo.append(config != null && result.contains("${")
                        ? config.getStrSubstitutor().replace(event, result) : result);
            } else {
                toAppendTo.append("null");
            }
        }
    }
