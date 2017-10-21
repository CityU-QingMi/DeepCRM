    @Test
    public void testStartOnTimeoutErrorDispatch() throws Exception
    {
        String response=process("start=200&timeout=error&error=dispatch",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "error",
            "onError",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onComplete"));

        assertContains("DISPATCHED",response);
    }
