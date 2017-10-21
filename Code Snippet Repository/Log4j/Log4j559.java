    @Test
    public void trimToMaxSizeWithLargeCapacity() throws Exception {
        final StringBuilder sb = new StringBuilder();
        final char[] value = new char[4 * 1024];
        sb.append(value);
        sb.setLength(0);

        assertTrue("needs trimming", sb.capacity() > Constants.MAX_REUSABLE_MESSAGE_SIZE);
        StringBuilders.trimToMaxSize(sb, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        assertTrue("trimmed OK", sb.capacity() <= Constants.MAX_REUSABLE_MESSAGE_SIZE);
    }
