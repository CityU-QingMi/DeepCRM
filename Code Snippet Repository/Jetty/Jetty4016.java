    @Test
    public void testStartOnTimeoutComplete() throws Exception
    {
        String response=process("start=200&timeout=complete",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "complete",
            "onComplete"));

        assertContains("COMPLETED",response);
    }
