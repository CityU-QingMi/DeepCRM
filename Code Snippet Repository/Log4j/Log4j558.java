    @Test
    public void trimToMaxSize() throws Exception {
        final StringBuilder sb = new StringBuilder();
        final char[] value = new char[4 * 1024];
        sb.append(value);

        assertTrue("needs trimming", sb.length() > Constants.MAX_REUSABLE_MESSAGE_SIZE);
        StringBuilders.trimToMaxSize(sb, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        assertTrue("trimmed OK", sb.length() <= Constants.MAX_REUSABLE_MESSAGE_SIZE);
    }
