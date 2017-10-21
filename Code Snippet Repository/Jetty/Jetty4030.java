    @Test
    public void testStartDispatchEncodedPath() throws Exception
    {
        String response=process("start=200&dispatch=20&path=/p%20th3",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "dispatch",
            "ASYNC /ctx/p%20th3",
            "!initial",
            "onComplete"));
        assertContains("DISPATCHED",response);
    }
