    @Test
    public void testRoutingAppenderRoutes() {
        final RoutingAppender routingAppender = getRoutingAppender();
        Assert.assertEquals(expectBindingEntries, routingAppender.getDefaultRouteScript() != null);
        Assert.assertEquals(expectBindingEntries, routingAppender.getDefaultRoute() != null);
        final Routes routes = routingAppender.getRoutes();
        Assert.assertNotNull(routes);
        Assert.assertNotNull(routes.getPatternScript());
        final LogEvent logEvent = DefaultLogEventFactory.getInstance().createEvent("", null, "", Level.ERROR, null,
                null, null);
        Assert.assertEquals("Service2", routes.getPattern(logEvent, new ConcurrentHashMap<>()));
    }
