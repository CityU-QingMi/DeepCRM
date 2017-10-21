    @Test
    public void testGetRouteEnqueuesIfThresholdCapacityReachedButLevelMoreSpecificThanThreshold()
            throws Exception {
        final DiscardingAsyncQueueFullPolicy router = new DiscardingAsyncQueueFullPolicy(Level.WARN);

        for (final Level level : new Level[] {Level.ERROR, Level.FATAL, Level.OFF}) {
            assertEquals(level.name(), EventRoute.SYNCHRONOUS, router.getRoute(currentThreadId(), level));
            assertEquals(level.name(), EventRoute.SYNCHRONOUS, router.getRoute(otherThreadId(), level));
            assertEquals(level.name(), EventRoute.SYNCHRONOUS, router.getRoute(currentThreadId(), level));
            assertEquals(level.name(), EventRoute.SYNCHRONOUS, router.getRoute(otherThreadId(), level));
        }
    }
