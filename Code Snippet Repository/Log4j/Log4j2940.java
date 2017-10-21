    @Test
    public void mdc() {

        MDC.put("TestYear", "2010");
        logger.debug("Debug message");
        verify("List", "o.a.l.s.LoggerTest Debug message MDC{TestYear=2010}" + Strings.LINE_SEPARATOR);
        MDC.clear();
        logger.debug("Debug message");
        verify("List", "o.a.l.s.LoggerTest Debug message MDC{}" + Strings.LINE_SEPARATOR);
    }
