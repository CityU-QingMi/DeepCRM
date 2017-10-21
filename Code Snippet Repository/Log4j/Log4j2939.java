    @Test
    public void debugNoParms() {
        logger.debug("Debug message {}");
        verify("List", "o.a.l.s.LoggerTest Debug message {} MDC{}" + Strings.LINE_SEPARATOR);
        logger.debug("Debug message {}", (Object[]) null);
        verify("List", "o.a.l.s.LoggerTest Debug message {} MDC{}" + Strings.LINE_SEPARATOR);
        ((LocationAwareLogger)logger).log(null, Log4jLogger.class.getName(), LocationAwareLogger.DEBUG_INT,
            "Debug message {}", null, null);
        verify("List", "o.a.l.s.LoggerTest Debug message {} MDC{}" + Strings.LINE_SEPARATOR);
    }
