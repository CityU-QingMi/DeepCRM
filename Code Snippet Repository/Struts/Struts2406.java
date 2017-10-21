    public void testNoLocation() throws Exception {
        try {
            result.setParse(false);
            result.setStylesheetLocation(null);
            result.execute(mai);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // success
        }
    }
