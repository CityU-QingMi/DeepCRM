    @Test
    public void testFwdStartDispatch() throws Exception
    {
        String response=process("fwd","start=200&dispatch=20",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "FWD REQUEST /ctx/fwd/info",
            "FORWARD /ctx/path1",
            "initial",
            "start",
            "dispatch",
            "FWD ASYNC /ctx/fwd/info",
            "FORWARD /ctx/path1",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
