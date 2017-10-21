    @Test
    public void shouldAddEntriesToBothStackAndMap() throws Exception {
        final String stackValue = "something";
        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.put(key, value).push(stackValue)) {
            assertThat(ThreadContext.get(key), is(value));
            assertThat(ThreadContext.peek(), is(stackValue));
        }
        assertThat(ThreadContext.containsKey(key), is(false));
        assertThat(ThreadContext.peek(), is(""));
    }
