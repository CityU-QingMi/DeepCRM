    @Test
    public void ifTheSameKeyIsAddedTwiceTheOriginalShouldBeUsed() throws Exception {
        final String oldValue = "oldValue";
        final String secondValue = "innerValue";
        ThreadContext.put(key, oldValue);
        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.put(key, value).put(key, secondValue)) {
            assertThat(ThreadContext.get(key), is(secondValue));
        }
        assertThat(ThreadContext.get(key), is(oldValue));
    }
