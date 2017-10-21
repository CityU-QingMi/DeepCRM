    private void failover(final LogEvent event, final Exception ex) {
        final RuntimeException re = ex != null ?
                (ex instanceof LoggingException ? (LoggingException) ex : new LoggingException(ex)) : null;
        boolean written = false;
        Exception failoverException = null;
        for (final AppenderControl control : failoverAppenders) {
            try {
                control.callAppender(event);
                written = true;
                break;
            } catch (final Exception fex) {
                if (failoverException == null) {
                    failoverException = fex;
                }
            }
        }
        if (!written && !ignoreExceptions()) {
            if (re != null) {
                throw re;
            }
            throw new LoggingException("Unable to write to failover appenders", failoverException);
        }
    }
