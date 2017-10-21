    private void appendMessageId(final StringBuilder buffer, final Message message) {
        final boolean isStructured = message instanceof StructuredDataMessage;
        final String type = isStructured ? ((StructuredDataMessage) message).getType() : null;
        if (type != null) {
            buffer.append(type);
        } else if (messageId != null) {
            buffer.append(messageId);
        } else {
            buffer.append('-');
        }
    }
