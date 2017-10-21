    private void handleLogMessageException(final Exception exception, final String fqcn, final Message msg) {
        if (exception instanceof LoggingException) {
            throw (LoggingException) exception;
        }
        final String format = msg.getFormat();
        final StringBuilder sb = new StringBuilder(format.length() + 100);
        sb.append(fqcn);
        sb.append(" caught ");
        sb.append(exception.getClass().getName());
        sb.append(" logging ");
        sb.append(msg.getClass().getSimpleName());
        sb.append(": ");
        sb.append(format);
        StatusLogger.getLogger().warn(sb.toString(), exception);
    }
