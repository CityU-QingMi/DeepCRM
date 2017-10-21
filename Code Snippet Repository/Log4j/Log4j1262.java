    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilder buf = getStringBuilder();
        appendPriority(buf, event.getLevel());
        appendTimestamp(buf, event.getTimeMillis());
        appendSpace(buf);
        appendHostName(buf);
        appendSpace(buf);
        appendAppName(buf);
        appendSpace(buf);
        appendProcessId(buf);
        appendSpace(buf);
        appendMessageId(buf, event.getMessage());
        appendSpace(buf);
        appendStructuredElements(buf, event);
        appendMessage(buf, event);
        if (useTlsMessageFormat) {
            return new TlsSyslogFrame(buf.toString()).toString();
        }
        return buf.toString();
    }
