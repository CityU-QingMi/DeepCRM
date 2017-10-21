    @Test
    public void testLog() {
        final Log logger = LogFactory.getLog("LoggerTest");
        logger.debug("Test message");
        verify("List", "o.a.l.l.j.LoggerTest Test message MDC{}" + Strings.LINE_SEPARATOR);
        logger.debug("Exception: " , new NullPointerException("Test"));
        verify("List", "o.a.l.l.j.LoggerTest Exception:  MDC{}" + Strings.LINE_SEPARATOR);
        logger.info("Info Message");
        verify("List", "o.a.l.l.j.LoggerTest Info Message MDC{}" + Strings.LINE_SEPARATOR);
        logger.info("Info Message {}");
        verify("List", "o.a.l.l.j.LoggerTest Info Message {} MDC{}" + Strings.LINE_SEPARATOR);
    }
