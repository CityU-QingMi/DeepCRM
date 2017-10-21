    public List<PatternFormatter> parse(final String pattern, final boolean alwaysWriteExceptions,
           final boolean disableAnsi, final boolean noConsoleNoAnsi) {
        final List<PatternFormatter> list = new ArrayList<>();
        final List<PatternConverter> converters = new ArrayList<>();
        final List<FormattingInfo> fields = new ArrayList<>();

        parse(pattern, converters, fields, disableAnsi, noConsoleNoAnsi, true);

        final Iterator<FormattingInfo> fieldIter = fields.iterator();
        boolean handlesThrowable = false;

        for (final PatternConverter converter : converters) {
            if (converter instanceof NanoTimePatternConverter) {
                // LOG4J2-1074 Switch to actual clock if nanosecond timestamps are required in config.
                // LOG4J2-1248 set config nanoclock
                if (config != null) {
                    config.setNanoClock(new SystemNanoClock());
                }
            }
            LogEventPatternConverter pc;
            if (converter instanceof LogEventPatternConverter) {
                pc = (LogEventPatternConverter) converter;
                handlesThrowable |= pc.handlesThrowable();
            } else {
                pc = new LiteralPatternConverter(config, Strings.EMPTY, true);
            }

            FormattingInfo field;
            if (fieldIter.hasNext()) {
                field = fieldIter.next();
            } else {
                field = FormattingInfo.getDefault();
            }
            list.add(new PatternFormatter(pc, field));
        }
        if (alwaysWriteExceptions && !handlesThrowable) {
            final LogEventPatternConverter pc = ExtendedThrowablePatternConverter.newInstance(config, null);
            list.add(new PatternFormatter(pc, FormattingInfo.getDefault()));
        }
        return list;
    }
