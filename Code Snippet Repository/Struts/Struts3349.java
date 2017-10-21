    public void testInfiniteLoop() throws JSONException {
        try {
            JSONReader reader = new JSONReader();
            reader.read("[1,\"a]");
            fail("Should have thrown an exception");
        } catch (JSONException e) {
            // I can't get JUnit to ignore the exception
            // @Test(expected = JSONException.class)
        }
    }
