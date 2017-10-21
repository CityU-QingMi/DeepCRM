    public void testGetSnippetNoPadding() throws Exception {
        URL url = ClassLoaderUtil.getResource("com/opensymphony/xwork2/somefile.txt", getClass());
        Location loc = new LocationImpl("foo", url.toString(), 3, 2);
        
        List snippet = loc.getSnippet(0);
        assertNotNull(snippet);
        assertTrue("Wrong length: "+snippet.size(), 1 == snippet.size());
        
        assertTrue("a".equals(snippet.get(0)));
    }
