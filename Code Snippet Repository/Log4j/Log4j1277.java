    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilder buf = getStringBuilder();

        buf.append('<');
        buf.append(Priority.getPriority(facility, event.getLevel()));
        buf.append('>');
        addDate(event.getTimeMillis(), buf);
        buf.append(Chars.SPACE);
        buf.append(localHostname);
        buf.append(Chars.SPACE);

        String message = event.getMessage().getFormattedMessage();
        if (null != escapeNewLine) {
            message = NEWLINE_PATTERN.matcher(message).replaceAll(escapeNewLine);
        }
        buf.append(message);

        if (includeNewLine) {
            buf.append('\n');
        }
        return buf.toString();
    }
