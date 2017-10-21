    @Test
    public void testAcceptOnceThresholdExceeded() {
        final DummyFileAttributes attribs = new DummyFileAttributes();
        final String[] sizes = {"2KB", "3MB", "4GB"};
        for (final String size : sizes) {
            final IfAccumulatedFileSize condition = IfAccumulatedFileSize.createFileSizeCondition(size);
            final long quarter = condition.getThresholdBytes() / 4;
            attribs.size = quarter;
            assertFalse(condition.accept(null, null, attribs));
            assertFalse(condition.accept(null, null, attribs));
            assertFalse(condition.accept(null, null, attribs));
            assertFalse(condition.accept(null, null, attribs));
            assertTrue(condition.accept(null, null, attribs));
        }
    }
