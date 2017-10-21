    public final void formatFileName(final StrSubstitutor subst, final StringBuilder buf, final boolean useCurrentTime,
                                     final Object obj) {
        // LOG4J2-628: we deliberately use System time, not the log4j.Clock time
        // for creating the file name of rolled-over files.
        final long time = useCurrentTime && currentFileTime != 0 ? currentFileTime :
                prevFileTime != 0 ? prevFileTime : System.currentTimeMillis();
        formatFileName(buf, new Date(time), obj);
        final LogEvent event = new Log4jLogEvent.Builder().setTimeMillis(time).build();
        final String fileName = subst.replace(event, buf);
        buf.setLength(0);
        buf.append(fileName);
    }
