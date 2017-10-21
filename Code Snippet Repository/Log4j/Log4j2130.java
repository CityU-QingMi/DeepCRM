    @Test
    public void testNoConcurrentModificationBiConsumerRemove() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "aaa");
        original.putValue("c", "aaa");
        original.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(final String s, final Object o) {
                original.remove("a");
            }
        });
    }
