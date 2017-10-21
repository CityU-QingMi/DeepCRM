    public void testGetSnippet() throws Exception {
        URL url = ClassLoaderUtil.getResource("com/opensymphony/xwork2/somefile.txt", getClass());
        Location loc = new LocationImpl("foo", url.toString(), 3, 2);
        
        List snippet = loc.getSnippet(1);
        assertNotNull(snippet);
        assertTrue("Wrong length: "+snippet.size(), 3 == snippet.size());
        
        assertTrue("is".equals(snippet.get(0)));
        assertTrue("a".equals(snippet.get(1)));
        assertTrue("file".equals(snippet.get(2)));
    }
