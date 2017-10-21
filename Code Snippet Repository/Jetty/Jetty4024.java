    @Test
    public void testNormal() throws Exception
    {
        String response=process(null,null);
        assertThat(response,startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
                "REQUEST /ctx/path/info",
                "initial"));
        assertContains("NORMAL",response);
        assertFalse(__history.contains("onTimeout"));
        assertFalse(__history.contains("onComplete"));
    }
