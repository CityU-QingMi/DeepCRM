    @Test
    public void testSuspendTimeoutSuspend() throws Exception
    {
        String response = process("suspend=10&suspend2=10", null);
        assertThat(response, startsWith("HTTP/1.1 200 OK"));
        assertThat(response, containsString("TIMEOUT"));
        assertEquals(2, count(history, "suspend"));
        assertEquals(0, count(history, "resume"));
        assertEquals(2, count(history, "onTimeout"));
        assertEquals(1, count(history, "onComplete"));
    }
