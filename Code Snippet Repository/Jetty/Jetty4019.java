    @Test
    public void testStartError() throws Exception
    {
        _expectedCode="500 ";
        String response=process("start=200&throw=1",null);
        assertThat(response,startsWith("HTTP/1.1 500 Server Error"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onError",
            "ERROR /ctx/error/custom",
            "!initial",
            "onComplete"));
        assertContains("ERROR DISPATCH: /ctx/error/custom",response);
    }
