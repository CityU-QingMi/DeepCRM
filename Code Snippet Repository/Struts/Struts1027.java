    public void testHasKey() throws Exception {
        assertTrue(textProvider.hasKey("name"));
        assertTrue(textProvider.hasKey("age"));
        assertTrue(textProvider.hasKey("cat"));
        assertTrue(textProvider.hasKey("dog"));
        assertTrue(textProvider.hasKey("car"));
        assertTrue(textProvider.hasKey("bike"));
        assertTrue(textProvider.hasKey("goodnight"));
        assertTrue(textProvider.hasKey("goodmorning"));
        assertFalse(textProvider.hasKey("nosuchkey"));
    }
