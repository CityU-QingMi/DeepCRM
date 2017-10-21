    @Test
    public void testStartWaitDispatch() throws Exception
    {
        String response=process("start=200&dispatch=10",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/path/info",
            "!initial",
            "onComplete"));
        assertFalse(__history.contains("onTimeout"));
    }
