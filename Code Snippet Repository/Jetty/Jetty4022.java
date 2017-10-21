    @Test
    public void testStartWaitDispatchStartWaitDispatch() throws Exception
    {
        String response=process("start=1000&dispatch=10&start2=1000&dispatch2=10",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onStartAsync",
            "start",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
