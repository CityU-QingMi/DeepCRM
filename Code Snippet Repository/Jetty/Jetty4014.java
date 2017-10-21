    @Test
    public void testStartOnTimeoutErrorComplete() throws Exception
    {
        String response=process("start=200&timeout=error&error=complete",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "error",
            "onError",
            "complete",
            "onComplete"));

        assertContains("COMPLETED",response);
    }
