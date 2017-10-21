    public String getSuppressedStackTrace(final String suffix) {
        final ThrowableProxy[] suppressed = this.getSuppressedProxies();
        if (suppressed == null || suppressed.length == 0) {
            return Strings.EMPTY;
        }
        final StringBuilder sb = new StringBuilder("Suppressed Stack Trace Elements:").append(EOL);
        for (final ThrowableProxy proxy : suppressed) {
            sb.append(proxy.getExtendedStackTraceAsString(suffix));
        }
        return sb.toString();
    }
