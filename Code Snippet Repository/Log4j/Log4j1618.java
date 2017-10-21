    @Test
    public void testTwoParents() throws IOException {
        final Marker expected = MarkerManager.getMarker("A");
        final Marker parent1 = MarkerManager.getMarker("PARENT_MARKER1");
        final Marker parent2 = MarkerManager.getMarker("PARENT_MARKER2");
        expected.addParents(parent1);
        expected.addParents(parent2);
        final String str = writeValueAsString(expected);
        Assert.assertTrue(str.contains("PARENT_MARKER1"));
        Assert.assertTrue(str.contains("PARENT_MARKER2"));
        final Marker actual = reader.readValue(str);
        Assert.assertEquals(expected, actual);
    }
