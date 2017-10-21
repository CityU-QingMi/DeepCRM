    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationBiConsumerPut() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "aaa");
        original.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(final String s, final Object o) {
                original.putValue("c", "other");
            }
        });
    }
