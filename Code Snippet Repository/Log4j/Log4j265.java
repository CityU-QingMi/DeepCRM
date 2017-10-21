    @Test
    public void closeIsIdempotent() throws Exception {

        final String originalMapValue = "map to keep";
        final String originalStackValue = "stack to keep";
        ThreadContext.put(key, originalMapValue);
        ThreadContext.push(originalStackValue);

        final String newMapValue = "temp map value";
        final String newStackValue = "temp stack to keep";
        final CloseableThreadContext.Instance ctc = CloseableThreadContext.push(newStackValue).put(key, newMapValue);

        ctc.close();
        assertThat(ThreadContext.get(key), is(originalMapValue));
        assertThat(ThreadContext.peek(), is(originalStackValue));

        ctc.close();
        assertThat(ThreadContext.get(key), is(originalMapValue));
        assertThat(ThreadContext.peek(), is(originalStackValue));
    }
