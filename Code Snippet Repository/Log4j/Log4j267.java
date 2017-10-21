    @Test
    public void pushAllWillPushAllValues() throws Exception {

        ThreadContext.push(key);
        final List<String> messages = ThreadContext.getImmutableStack().asList();
        ThreadContext.pop();

        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.pushAll(messages)) {
            assertThat(ThreadContext.peek(), is(key));
        }
        assertThat(ThreadContext.peek(), is(""));

    }
