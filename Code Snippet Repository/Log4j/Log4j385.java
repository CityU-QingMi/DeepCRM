    @Override
    public void logMessage(final String fqcn, final Level level, final Marker marker, final Message msg, final Throwable throwable) {
        final StringBuilder sb = new StringBuilder();
        if (marker != null) {
            sb.append(marker);
        }
        sb.append(' ');
        sb.append(level.toString());
        sb.append(' ');
        sb.append(msg.getFormattedMessage());
        final Map<String, String> mdc = ThreadContext.getImmutableContext();
        if (mdc.size() > 0) {
            sb.append(' ');
            sb.append(mdc.toString());
            sb.append(' ');
        }
        final Object[] params = msg.getParameters();
        Throwable t;
        if (throwable == null && params != null && params.length > 0 && params[params.length - 1] instanceof Throwable) {
            t = (Throwable) params[params.length - 1];
        } else {
            t = throwable;
        }
        if (t != null) {
            sb.append(' ');
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            t.printStackTrace(new PrintStream(baos));
            sb.append(baos.toString());
        }
        list.add(sb.toString());
        //System.out.println(sb.toString());
    }
