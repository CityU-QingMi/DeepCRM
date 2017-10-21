    @Test
    public void testAsyncNotSupportedNoAsync() throws Exception
    {
        _expectedCode="200 ";
        String response=process("noasync","",null);
        Assert.assertThat(response,Matchers.startsWith("HTTP/1.1 200 OK"));
        assertThat(__history,contains(
            "REQUEST /ctx/noasync/info",
            "initial"
            ));

        assertContains("NORMAL",response);
    }
