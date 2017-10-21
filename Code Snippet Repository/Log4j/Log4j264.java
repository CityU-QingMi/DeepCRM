    @Test
    public void canReuseCloseableThreadContext() throws Exception {
        final String stackValue = "something";
        // Create a ctc and close it
        final CloseableThreadContext.Instance ctc = CloseableThreadContext.push(stackValue).put(key, value);
        assertThat(ThreadContext.get(key), is(value));
        assertThat(ThreadContext.peek(), is(stackValue));
        ctc.close();

        assertThat(ThreadContext.containsKey(key), is(false));
        assertThat(ThreadContext.peek(), is(""));

        final String anotherKey = "key2";
        final String anotherValue = "value2";
        final String anotherStackValue = "something else";
        // Use it again
        ctc.push(anotherStackValue).put(anotherKey, anotherValue);
        assertThat(ThreadContext.get(anotherKey), is(anotherValue));
        assertThat(ThreadContext.peek(), is(anotherStackValue));
        ctc.close();

        assertThat(ThreadContext.containsKey(anotherKey), is(false));
        assertThat(ThreadContext.peek(), is(""));
    }
