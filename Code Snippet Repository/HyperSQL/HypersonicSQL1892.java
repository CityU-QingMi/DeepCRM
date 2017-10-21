    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        if (withTime) {
            sb.append(sdf.format(new Date(record.getMillis())) + "  ");
        }
        sb.append(record.getLevel() + "  " + formatMessage(record));
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            record.getThrown().printStackTrace(new PrintWriter(sw));
            sb.append(LS + sw);
        }
        return sb.toString() + LS;
        // This uses platform-specific line-separator, the same as
        // SimpleLogger does.
    }
