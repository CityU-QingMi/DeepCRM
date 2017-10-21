    private void appendMessage(final StringBuilder buffer, final LogEvent event) {
        final Message message = event.getMessage();
        // This layout formats StructuredDataMessages instead of delegating to the Message itself.
        final String text = (message instanceof StructuredDataMessage || message instanceof MessageCollectionMessage)
                ? message.getFormat() : message.getFormattedMessage();

        if (text != null && text.length() > 0) {
            buffer.append(' ').append(escapeNewlines(text, escapeNewLine));
        }

        if (exceptionFormatters != null && event.getThrown() != null) {
            final StringBuilder exception = new StringBuilder(LF);
            for (final PatternFormatter formatter : exceptionFormatters) {
                formatter.format(event, exception);
            }
            buffer.append(escapeNewlines(exception.toString(), escapeNewLine));
        }
        if (includeNewLine) {
            buffer.append(LF);
        }
    }
