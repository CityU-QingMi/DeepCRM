    @Test
    public void testWrapStartDispatch() throws Exception
    {
        String response=process("wrap=true&start=200&dispatch=20",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path/info",
            "wrapped REQ RSP",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
