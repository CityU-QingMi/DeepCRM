    @Test
    public void testEventLogger() {
        MDC.put("loginId", "JohnDoe");
        MDC.put("ipAddress", "192.168.0.120");
        MDC.put("locale", Locale.US.getDisplayName());
        final EventData data = new EventData();
        data.setEventType("Transfer");
        data.setEventId("Audit@18060");
        data.setMessage("Transfer Complete");
        data.put("ToAccount", "123456");
        data.put("FromAccount", "123457");
        data.put("Amount", "200.00");
        EventLogger.logEvent(data);
        MDC.clear();
        verify("EventLogger", "o.a.l.s.LoggerTest Transfer [Audit@18060 Amount=\"200.00\" FromAccount=\"123457\" ToAccount=\"123456\"] Transfer Complete" + Strings.LINE_SEPARATOR);
    }
