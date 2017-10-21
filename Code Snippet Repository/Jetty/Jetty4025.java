    @Test
    public void testStartWaitDispatchStart() throws Exception
    {
        _expectedCode="500 ";
        String response=process("start=1000&dispatch=10&start2=10",null);
        assertThat(response,startsWith("HTTP/1.1 500 Server Error"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onStartAsync",
            "start",
            "onTimeout",
            "ERROR /ctx/error/custom",
            "!initial",
            "onComplete"));
        assertContains("ERROR DISPATCH: /ctx/error/custom",response);
    }
