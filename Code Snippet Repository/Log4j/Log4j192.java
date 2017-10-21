    public String getFormattedStatus() {
        final StringBuilder sb = new StringBuilder();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        sb.append(format.format(new Date(timestamp)));
        sb.append(SPACE);
        sb.append(getThreadName());
        sb.append(SPACE);
        sb.append(level.toString());
        sb.append(SPACE);
        sb.append(msg.getFormattedMessage());
        final Object[] params = msg.getParameters();
        Throwable t;
        if (throwable == null && params != null && params[params.length - 1] instanceof Throwable) {
            t = (Throwable) params[params.length - 1];
        } else {
            t = throwable;
        }
        if (t != null) {
            sb.append(SPACE);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            t.printStackTrace(new PrintStream(baos));
            sb.append(baos.toString());
        }
        return sb.toString();
    }
