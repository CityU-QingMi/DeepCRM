    @Test
    public void testStartTimeoutStartComplete() throws Exception
    {
        String response=process("start=10&start2=1000&complete2=10",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "ERROR /ctx/error/custom",
            "!initial",
            "onStartAsync",
            "start",
            "complete",
            "onComplete"));
        assertContains("COMPLETED",response);
    }
