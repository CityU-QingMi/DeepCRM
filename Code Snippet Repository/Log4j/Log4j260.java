    @Test
    public void shouldNestEntries() throws Exception {
        final String oldValue = "oldValue";
        final String innerValue = "innerValue";
        ThreadContext.put(key, oldValue);
        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.put(key, value)) {
            assertThat(ThreadContext.get(key), is(value));
            try (final CloseableThreadContext.Instance ignored2 = CloseableThreadContext.put(key, innerValue)) {
                assertThat(ThreadContext.get(key), is(innerValue));
            }
            assertThat(ThreadContext.get(key), is(value));
        }
        assertThat(ThreadContext.get(key), is(oldValue));
    }
