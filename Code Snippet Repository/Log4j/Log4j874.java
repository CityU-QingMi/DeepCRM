    private LogEvent prepareEvent(final LogEvent event) {
        LogEvent logEvent = ensureImmutable(event);
        if (logEvent.getMessage() instanceof ReusableMessage) {
            if (logEvent instanceof Log4jLogEvent) {
                ((Log4jLogEvent) logEvent).makeMessageImmutable();
            } else if (logEvent instanceof MutableLogEvent) {
                // MutableLogEvents need to be translated into the RingBuffer by the MUTABLE_TRANSLATOR.
                // That translator calls MutableLogEvent.initFrom to copy the event, which will makeMessageImmutable the message.
                if (translator != MUTABLE_TRANSLATOR) { // should not happen...
                    // TRANSLATOR expects an immutable LogEvent
                    logEvent = ((MutableLogEvent) logEvent).createMemento();
                }
            } else { // custom log event, with a ReusableMessage
                showWarningAboutCustomLogEventWithReusableMessage(logEvent);
            }
        } else { // message is not a ReusableMessage; makeMessageImmutable it to prevent ConcurrentModificationExceptions
            InternalAsyncUtil.makeMessageImmutable(logEvent.getMessage()); // LOG4J2-1988, LOG4J2-1914
        }
        return logEvent;
    }
