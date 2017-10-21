    public void testStreamResultParseNoInputName() throws Exception {
        result.setParse(true);
        result.setInputName("${top}");

        try {
            result.doExecute("helloworld", mai);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // success
        }
    }
