    @Test
    public void testAccept() {
        final int[] counts = {3, 5, 9};
        for (final int count : counts) {
            final IfAccumulatedFileCount condition = IfAccumulatedFileCount.createFileCountCondition(count);
            for (int i = 0; i < count; i++) {
                assertFalse(condition.accept(null, null, null));
                // exact match: does not accept
            }
            // accept when threshold is exceeded
            assertTrue(condition.accept(null, null, null));
            assertTrue(condition.accept(null, null, null));
        }
    }
