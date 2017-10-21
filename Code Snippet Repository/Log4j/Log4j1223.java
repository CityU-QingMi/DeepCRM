    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilder buffer = getStringBuilder();
        final CSVFormat format = getFormat();
        try {
            format.print(event.getNanoTime(), buffer, true);
            format.print(event.getTimeMillis(), buffer, false);
            format.print(event.getLevel(), buffer, false);
            format.print(event.getThreadId(), buffer, false);
            format.print(event.getThreadName(), buffer, false);
            format.print(event.getThreadPriority(), buffer, false);
            format.print(event.getMessage().getFormattedMessage(), buffer, false);
            format.print(event.getLoggerFqcn(), buffer, false);
            format.print(event.getLoggerName(), buffer, false);
            format.print(event.getMarker(), buffer, false);
            format.print(event.getThrownProxy(), buffer, false);
            format.print(event.getSource(), buffer, false);
            format.print(event.getContextData(), buffer, false);
            format.print(event.getContextStack(), buffer, false);
            format.println(buffer);
            return buffer.toString();
        } catch (final IOException e) {
            StatusLogger.getLogger().error(event.toString(), e);
            return format.getCommentMarker() + " " + e;
        }
    }
