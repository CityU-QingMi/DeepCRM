    @Test
    public void testStartWaitDispatchStartComplete() throws Exception
    {
        String response=process("start=1000&dispatch=10&start2=1000&complete2=10",null);
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
            "complete",
            "onComplete"));
        assertContains("COMPLETED",response);
    }
