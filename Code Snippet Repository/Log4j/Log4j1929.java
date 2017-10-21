    private ListAppender getListAppender() {
        final String key = "Service2";
        final RoutingAppender routingAppender = getRoutingAppender();
        Assert.assertTrue(routingAppender.isStarted());
        final Map<String, AppenderControl> appenders = routingAppender.getAppenders();
        final AppenderControl appenderControl = appenders.get(key);
        assertNotNull("No appender control generated for '" + key + "'; appenders = " + appenders, appenderControl);
        final ListAppender listAppender = (ListAppender) appenderControl.getAppender();
        return listAppender;
    }
