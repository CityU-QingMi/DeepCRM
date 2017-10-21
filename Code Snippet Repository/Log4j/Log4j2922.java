    public LoggingApp(final String member) {

        ThreadContext.clearMap();

        RequestContext.setSessionId("session1234");
        RequestContext.setIpAddress("127.0.0.1");
        RequestContext.setClientId("02121");
        RequestContext.setProductName("IB");
        RequestContext.setProductVersion("4.18.1");
        RequestContext.setLocale("en_US");
        RequestContext.setRegion("prod");

        if (events == null) {
            events = MockEventsSupplier.getAllEvents(member);
        }
    }
