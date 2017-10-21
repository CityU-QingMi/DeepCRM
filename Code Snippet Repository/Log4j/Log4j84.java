    protected Message getMessage(final String msgPattern, final Object[] args, final Throwable aThrowable) {
        try {
            final MessageFormat format = new MessageFormat(msgPattern);
            final Format[] formats = format.getFormats();
            if (formats != null && formats.length > 0) {
                return new MessageFormatMessage(locale, msgPattern, args);
            }
        } catch (final Exception ignored) {
            // Obviously, the message is not a proper pattern for MessageFormat.
        }
        try {
            if (MSG_PATTERN.matcher(msgPattern).find()) {
                return new StringFormattedMessage(locale, msgPattern, args);
            }
        } catch (final Exception ignored) {
            // Also not properly formatted.
        }
        return new ParameterizedMessage(msgPattern, args, aThrowable);
    }
