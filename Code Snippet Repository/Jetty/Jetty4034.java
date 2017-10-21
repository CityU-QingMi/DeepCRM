    @Test
    public void testFwdWrapStartDispatchPath() throws Exception
    {
        String response=process("fwd","wrap=true&start=200&dispatch=20&path=/path2",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "FWD REQUEST /ctx/fwd/info",
            "FORWARD /ctx/path1",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path2",
            "wrapped REQ RSP",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
