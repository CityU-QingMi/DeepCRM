    public void testStreamResultNoInputName() throws Exception {
        result.setParse(false);
        result.setInputName(null);

        try {
            result.doExecute("helloworld", mai);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // success
        }
    }
