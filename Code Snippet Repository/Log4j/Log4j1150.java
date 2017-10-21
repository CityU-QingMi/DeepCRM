    public static Log4jLogEvent deserialize(final Serializable event) {
        Objects.requireNonNull(event, "Event cannot be null");
        if (event instanceof LogEventProxy) {
            final LogEventProxy proxy = (LogEventProxy) event;
            final Log4jLogEvent result = new Log4jLogEvent(proxy.loggerName, proxy.marker,
                    proxy.loggerFQCN, proxy.level, proxy.message,
                    proxy.thrown, proxy.thrownProxy, proxy.contextData, proxy.contextStack, proxy.threadId,
                    proxy.threadName, proxy.threadPriority, proxy.source, proxy.timeMillis, proxy.nanoTime);
            result.setEndOfBatch(proxy.isEndOfBatch);
            result.setIncludeLocation(proxy.isLocationRequired);
            return result;
        }
        throw new IllegalArgumentException("Event is not a serialized LogEvent: " + event.toString());
    }
