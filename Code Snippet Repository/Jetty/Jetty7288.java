    @Test
    public void testSuspendWaitResumeSuspend() throws Exception
    {
        String response = process("suspend=1000&resume=10&suspend2=10", null);
        assertThat(response, startsWith("HTTP/1.1 200 OK"));
        assertThat(response, containsString("TIMEOUT"));
        assertEquals(2, count(history, "suspend"));
        assertEquals(1, count(history, "resume"));
        assertEquals(1, count(history, "onTimeout"));
        assertEquals(1, count(history, "onComplete"));
    }
