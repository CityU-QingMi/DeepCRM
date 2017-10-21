    @Test
    public void testStartWaitComplete() throws Exception
    {
        String response=process("start=200&complete=50",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "complete",
            "onComplete"));
        assertContains("COMPLETED",response);
        assertFalse(__history.contains("onTimeout"));
        assertFalse(__history.contains("!initial"));
    }
