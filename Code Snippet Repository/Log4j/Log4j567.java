    @Ignore
    @Test
    public void testBoxSuccessfullyConfiguredTo128Slots() throws Exception {
        final int MAX = 128;
        final StringBuilder[] probe = new StringBuilder[MAX * 3];
        for (int i = 0; i <= probe.length - 8; ) {
            probe[i++] = Unbox.box(true);
            probe[i++] = Unbox.box('c');
            probe[i++] = Unbox.box(Byte.MAX_VALUE);
            probe[i++] = Unbox.box(Double.MAX_VALUE);
            probe[i++] = Unbox.box(Float.MAX_VALUE);
            probe[i++] = Unbox.box(Integer.MAX_VALUE);
            probe[i++] = Unbox.box(Long.MAX_VALUE);
            probe[i++] = Unbox.box(Short.MAX_VALUE);
        }
        for (int i = 0; i < probe.length - MAX; i++) {
            assertSame("probe[" + i +"], probe[" + (i + MAX) +"]", probe[i], probe[i + MAX]);
            for (int j = 1; j < MAX - 1; j++) {
                assertNotSame("probe[" + i +"], probe[" + (i + j) +"]", probe[i], probe[i + j]);
            }
        }
    }
