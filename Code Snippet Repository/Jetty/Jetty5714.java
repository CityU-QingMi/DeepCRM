    @Override
    public void publish(LogRecord record)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(record.getLevel().getName()).append("|");
        buf.append(record.getLoggerName()).append("|");
        buf.append(record.getMessage());

        output.append(buf);
        if (record.getMessage().length() > 0)
        {
            output.append(LN);
        }

        if (record.getThrown() != null)
        {
            StringWriter sw = new StringWriter(128);
            PrintWriter capture = new PrintWriter(sw);
            record.getThrown().printStackTrace(capture);
            capture.flush();
            output.append(sw.toString());
            IO.close(capture);
        }
    }
