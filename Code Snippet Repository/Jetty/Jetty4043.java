    @Test
    public void testStart() throws Exception
    {
        _expectedCode="500 ";
        String response=process("start=200",null);
        Assert.assertThat(response,Matchers.startsWith("HTTP/1.1 500 Server Error"));
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "ERROR /ctx/error/custom",
            "!initial",
            "onComplete"));

        assertContains("ERROR DISPATCH: /ctx/error/custom",response);
    }
