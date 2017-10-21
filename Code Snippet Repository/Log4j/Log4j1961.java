    @Test
    public void testGetRouteDiscardsIfThresholdCapacityReachedAndLevelEqualOrLessSpecificThanThreshold()
            throws Exception {
        final DiscardingAsyncQueueFullPolicy router = new DiscardingAsyncQueueFullPolicy(Level.WARN);

        for (final Level level : new Level[] {Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL}) {
            assertEquals(level.name(), EventRoute.DISCARD, router.getRoute(currentThreadId(), level));
            assertEquals(level.name(), EventRoute.DISCARD, router.getRoute(otherThreadId(), level));
            assertEquals(level.name(), EventRoute.DISCARD, router.getRoute(currentThreadId(), level));
            assertEquals(level.name(), EventRoute.DISCARD, router.getRoute(otherThreadId(), level));
        }
    }
