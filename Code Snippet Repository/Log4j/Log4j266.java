    @Test
    public void putAllWillPutAllValues() throws Exception {

        final String oldValue = "oldValue";
        ThreadContext.put(key, oldValue);

        final Map<String, String> valuesToPut = new HashMap<>();
        valuesToPut.put(key, value);

        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.putAll(valuesToPut)) {
            assertThat(ThreadContext.get(key), is(value));
        }
        assertThat(ThreadContext.get(key), is(oldValue));

    }
