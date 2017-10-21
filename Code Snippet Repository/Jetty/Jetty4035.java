    @Test
    public void testSleep() throws Exception
    {
        String response=process("sleep=200",null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
                "REQUEST /ctx/path/info",
                "initial"));
        assertContains("SLEPT",response);
        assertFalse(__history.contains("onTimeout"));
        assertFalse(__history.contains("onComplete"));
    }
