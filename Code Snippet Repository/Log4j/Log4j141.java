    public SimpleLogger(final String name, final Level defaultLevel, final boolean showLogName,
            final boolean showShortLogName, final boolean showDateTime, final boolean showContextMap,
            final String dateTimeFormat, final MessageFactory messageFactory, final PropertiesUtil props,
            final PrintStream stream) {
        super(name, messageFactory);
        final String lvl = props.getStringProperty(SimpleLoggerContext.SYSTEM_PREFIX + name + ".level");
        this.level = Level.toLevel(lvl, defaultLevel);
        if (showShortLogName) {
            final int index = name.lastIndexOf(".");
            if (index > 0 && index < name.length()) {
                this.logName = name.substring(index + 1);
            } else {
                this.logName = name;
            }
        } else if (showLogName) {
            this.logName = name;
        } else {
            this.logName = null;
        }
        this.showDateTime = showDateTime;
        this.showContextMap = showContextMap;
        this.stream = stream;

        if (showDateTime) {
            DateFormat format;
            try {
                format = new SimpleDateFormat(dateTimeFormat);
            } catch (final IllegalArgumentException e) {
                // If the format pattern is invalid - use the default format
                format = new SimpleDateFormat(SimpleLoggerContext.DEFAULT_DATE_TIME_FORMAT);
            }
            this.dateFormatter = format;
        } else {
            this.dateFormatter = null;
        }
    }
