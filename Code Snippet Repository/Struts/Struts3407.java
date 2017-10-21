    @Test
    public void testPersistingSessionValues() throws Exception {
        String output = executeAction("/sessiontest/sessionSet.action");
        Assert.assertEquals("sessionValue", output);

        this.finishExecution();

        String output2 = executeAction("/sessiontest/sessionGet.action");
        Assert.assertEquals("sessionValue", output2);
    }
