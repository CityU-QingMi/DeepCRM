    public void testNoCache() throws Exception {
        JSONResult result = new JSONResult();
        result.setNoCache(true);

        executeTest2Action(result);

        assertEquals("no-cache", response.getHeader("Cache-Control"));
        assertEquals("0", response.getHeader("Expires"));
        assertEquals("No-cache", response.getHeader("Pragma"));
    }
