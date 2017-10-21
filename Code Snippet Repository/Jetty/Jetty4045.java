    @Test
    public void testStartOnTimeoutError() throws Exception
    {
        _expectedCode="500 ";
        String response=process("start=200&timeout=error",null);
        assertThat(response,startsWith("HTTP/1.1 500 Server Error"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "error",
            "onError",
            "ERROR /ctx/error/custom",
            "!initial",
            "onComplete"));

        assertContains("ERROR DISPATCH",response);
    }
