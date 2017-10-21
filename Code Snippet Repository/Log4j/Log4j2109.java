    @Test
    public void testMarkers() {
        final Marker parent = MarkerManager.getMarker("Parent");
        final Marker child = MarkerManager.getMarker("Child").setParents(parent);
        final Marker grandChild = MarkerManager.getMarker("GrandChild").setParents(child);
        final Marker sibling = MarkerManager.getMarker("Sibling").setParents(parent);
        final Marker stranger = MarkerManager.getMarker("Stranger");
        MarkerFilter filter = MarkerFilter.createFilter("Parent", null, null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.DENY, filter.filter(null, null, stranger, (Object) null, (Throwable) null));
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, null, child, (Object) null, (Throwable) null));
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, null, grandChild, (Object) null, (Throwable) null));
        filter.stop();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setMarker(grandChild) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
        filter = MarkerFilter.createFilter("Child", null, null);
        filter.start();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
        event = Log4jLogEvent.newBuilder() //
                .setMarker(sibling) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertSame(Filter.Result.DENY, filter.filter(event));
        filter.stop();
    }
