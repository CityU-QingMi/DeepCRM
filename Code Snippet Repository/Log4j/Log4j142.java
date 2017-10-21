    @Override
    public void logMessage(final String fqcn, final Level mgsLevel, final Marker marker, final Message msg,
            final Throwable throwable) {
        final StringBuilder sb = new StringBuilder();
        // Append date-time if so configured
        if (showDateTime) {
            final Date now = new Date();
            String dateText;
            synchronized (dateFormatter) {
                dateText = dateFormatter.format(now);
            }
            sb.append(dateText);
            sb.append(SPACE);
        }

        sb.append(mgsLevel.toString());
        sb.append(SPACE);
        if (Strings.isNotEmpty(logName)) {
            sb.append(logName);
            sb.append(SPACE);
        }
        sb.append(msg.getFormattedMessage());
        if (showContextMap) {
            final Map<String, String> mdc = ThreadContext.getImmutableContext();
            if (mdc.size() > 0) {
                sb.append(SPACE);
                sb.append(mdc.toString());
                sb.append(SPACE);
            }
        }
        final Object[] params = msg.getParameters();
        Throwable t;
        if (throwable == null && params != null && params.length > 0
                && params[params.length - 1] instanceof Throwable) {
            t = (Throwable) params[params.length - 1];
        } else {
            t = throwable;
        }
        stream.println(sb.toString());
        if (t != null) {
            stream.print(SPACE);
            t.printStackTrace(stream);
        }
    }
