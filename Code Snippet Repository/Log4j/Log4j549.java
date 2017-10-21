    @Test
    public void testForEachBiConsumer() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        original.forEach(new BiConsumer<String, String>() {
            int count = 0;
            @Override
            public void accept(final String key, final String value) {
                assertEquals("key", key, original.getKeyAt(count));
                assertEquals("val", value, original.getValueAt(count));
                count++;
                assertTrue("count should not exceed size but was " + count, count <= original.size());
            }
        });
    }
