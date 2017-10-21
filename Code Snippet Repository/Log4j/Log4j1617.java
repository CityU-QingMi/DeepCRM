    @Test
    public void testOneParent() throws IOException {
        final Marker expected = MarkerManager.getMarker("A");
        final Marker parent = MarkerManager.getMarker("PARENT_MARKER");
        expected.addParents(parent);
        final String str = writeValueAsString(expected);
        Assert.assertTrue(str.contains("PARENT_MARKER"));
        final Marker actual = reader.readValue(str);
        Assert.assertEquals(expected, actual);
    }
