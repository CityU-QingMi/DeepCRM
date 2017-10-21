    @Test
    public void testStartTimeoutStart() throws Exception
    {
        _expectedCode="500 ";
        _errorHandler.addErrorPage(500,"/path/error");
        
        String response=process("start=10&start2=10",null);
        assertThat(__history,contains(
            "REQUEST /ctx/path/info",
            "initial",
            "start",
            "onTimeout",
            "ERROR /ctx/path/error",
            "!initial",
            "onStartAsync",
            "start",
            "onTimeout",
            "onComplete")); // Error Page Loop!
        assertContains("HTTP ERROR 500",response);
    }
