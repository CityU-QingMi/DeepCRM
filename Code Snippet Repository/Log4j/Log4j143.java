    public SimpleLoggerContext() {
        props = new PropertiesUtil("log4j2.simplelog.properties");

        showContextMap = props.getBooleanProperty(SYSTEM_PREFIX + "showContextMap", false);
        showLogName = props.getBooleanProperty(SYSTEM_PREFIX + "showlogname", false);
        showShortName = props.getBooleanProperty(SYSTEM_PREFIX + "showShortLogname", true);
        showDateTime = props.getBooleanProperty(SYSTEM_PREFIX + "showdatetime", false);
        final String lvl = props.getStringProperty(SYSTEM_PREFIX + "level");
        defaultLevel = Level.toLevel(lvl, Level.ERROR);

        dateTimeFormat = showDateTime ? props.getStringProperty(SimpleLoggerContext.SYSTEM_PREFIX + "dateTimeFormat",
                DEFAULT_DATE_TIME_FORMAT) : null;

        final String fileName = props.getStringProperty(SYSTEM_PREFIX + "logFile", SYSTEM_ERR);
        PrintStream ps;
        if (SYSTEM_ERR.equalsIgnoreCase(fileName)) {
            ps = System.err;
        } else if (SYSTEM_OUT.equalsIgnoreCase(fileName)) {
            ps = System.out;
        } else {
            try {
                final FileOutputStream os = new FileOutputStream(fileName);
                ps = new PrintStream(os);
            } catch (final FileNotFoundException fnfe) {
                ps = System.err;
            }
        }
        this.stream = ps;
    }
