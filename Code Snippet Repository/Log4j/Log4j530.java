    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationTriConsumerPutValue() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "aaa");
        original.forEach(new TriConsumer<String, Object, Object>() {
            @Override
            public void accept(final String s, final Object o, final Object o2) {
                original.putValue("c", "other");
            }
        }, null);
    }
