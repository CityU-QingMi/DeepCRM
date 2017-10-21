    @Test
    public void testStartOnTimeoutDispatch() throws Exception
    {
        String response=process("start=200&timeout=dispatch",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onComplete"));

        assertContains("DISPATCHED",response);
    }
