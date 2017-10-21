    @Test
    public void testFwdWrapStartDispatch() throws Exception
    {
        String response=process("fwd","wrap=true&start=200&dispatch=20",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "FWD REQUEST /ctx/fwd/info",
            "FORWARD /ctx/path1",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path1",
            "wrapped REQ RSP",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
