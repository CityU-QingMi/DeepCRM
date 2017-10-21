    @Test
    public void testMultipleParents() {
        final Marker parent1 = MarkerManager.getMarker("PARENT1");
        final Marker parent2 = MarkerManager.getMarker("PARENT2");
        final Marker test1 = MarkerManager.getMarker("TEST1").setParents(parent1, parent2);
        final Marker test2 = MarkerManager.getMarker("TEST2").addParents(parent1, parent2);
        assertTrue("TEST1 is not an instance of PARENT1", test1.isInstanceOf(parent1));
        assertTrue("TEST1 is not an instance of PARENT2", test1.isInstanceOf(parent2));
        assertTrue("TEST2 is not an instance of PARENT1", test2.isInstanceOf(parent1));
        assertTrue("TEST2 is not an instance of PARENT2", test2.isInstanceOf(parent2));
    }
