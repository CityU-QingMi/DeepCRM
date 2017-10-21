    @Test
    public void testGetMarkerWithParents() {
        final Marker expected = MarkerManager.getMarker("A");
        final Marker p1 = MarkerManager.getMarker("P1");
        p1.addParents(MarkerManager.getMarker("PP1"));
        final Marker p2 = MarkerManager.getMarker("P2");
        expected.addParents(p1);
        expected.addParents(p2);
        assertEquals(2, expected.getParents().length);
    }
