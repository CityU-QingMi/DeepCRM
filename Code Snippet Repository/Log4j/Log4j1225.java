    @Override
    public String toSerializable(final LogEvent event) {
        final Message message = event.getMessage();
        final Object[] parameters = message.getParameters();
        final StringBuilder buffer = getStringBuilder();
        try {
            getFormat().printRecord(buffer, parameters);
            return buffer.toString();
        } catch (final IOException e) {
            StatusLogger.getLogger().error(message, e);
            return getFormat().getCommentMarker() + " " + e;
        }
    }
