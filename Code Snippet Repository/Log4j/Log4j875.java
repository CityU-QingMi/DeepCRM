    private void showWarningAboutCustomLogEventWithReusableMessage(final LogEvent logEvent) {
        if (!alreadyLoggedWarning) {
            LOGGER.warn("Custom log event of type {} contains a mutable message of type {}." +
                            " AsyncLoggerConfig does not know how to make an immutable copy of this message." +
                            " This may result in ConcurrentModificationExceptions or incorrect log messages" +
                            " if the application modifies objects in the message while" +
                            " the background thread is writing it to the appenders.",
                    logEvent.getClass().getName(), logEvent.getMessage().getClass().getName());
            alreadyLoggedWarning = true;
        }
    }
