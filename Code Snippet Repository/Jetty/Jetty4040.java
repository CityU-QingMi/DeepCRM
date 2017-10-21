    @Test
    public void testNonAsync() throws Exception
    {
        String response=process("",null);
        Assert.assertThat(response,Matchers.startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial"));

        assertContains("NORMAL",response);
    }
