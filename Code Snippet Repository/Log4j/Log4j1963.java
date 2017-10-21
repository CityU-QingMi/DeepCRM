    @Test
    public void testGetDiscardCount() throws Exception {
        final DiscardingAsyncQueueFullPolicy router = new DiscardingAsyncQueueFullPolicy(Level.INFO);
        assertEquals("initially", 0, DiscardingAsyncQueueFullPolicy.getDiscardCount(router));

        assertEquals(EventRoute.DISCARD, router.getRoute(-1L, Level.INFO));
        assertEquals("increase", 1, DiscardingAsyncQueueFullPolicy.getDiscardCount(router));

        assertEquals(EventRoute.DISCARD, router.getRoute(-1L, Level.INFO));
        assertEquals("increase", 2, DiscardingAsyncQueueFullPolicy.getDiscardCount(router));

        assertEquals(EventRoute.DISCARD, router.getRoute(-1L, Level.INFO));
        assertEquals("increase", 3, DiscardingAsyncQueueFullPolicy.getDiscardCount(router));
    }
