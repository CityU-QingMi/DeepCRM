    @Test
    public void testAcceptIfExceedThreshold() {
        final String[] sizes = {"2KB", "3MB", "4GB"};
        for (final String size : sizes) {
            final IfAccumulatedFileSize condition = IfAccumulatedFileSize.createFileSizeCondition(size);
            final DummyFileAttributes attribs = new DummyFileAttributes();
            attribs.size = condition.getThresholdBytes() + 1;
            assertTrue(condition.accept(null, null, attribs));
        }
    }
