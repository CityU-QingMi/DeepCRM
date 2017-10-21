    @Test
    public void testSuspendComplete() throws Exception
    {
        String response = process("suspend=200&complete=0", null);
        assertThat(response, startsWith("HTTP/1.1 200 OK"));
        assertThat(response, containsString("COMPLETED"));
        assertThat(history, hasItem("initial"));
        assertThat(history, not(hasItem("!initial")));
        assertThat(history, not(hasItem("onTimeout")));
        assertThat(history, hasItem("onComplete"));
    }
