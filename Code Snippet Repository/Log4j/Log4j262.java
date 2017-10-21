    @Test
    public void shouldPushAndPopAParameterizedEntryToTheStack() throws Exception {
        final String parameterizedMessage = "message {}";
        final String parameterizedMessageParameter = "param";
        final String formattedMessage = parameterizedMessage.replace("{}", parameterizedMessageParameter);
        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.push(parameterizedMessage,
                parameterizedMessageParameter)) {
            assertThat(ThreadContext.peek(), is(formattedMessage));
        }
        assertThat(ThreadContext.peek(), is(""));
    }
