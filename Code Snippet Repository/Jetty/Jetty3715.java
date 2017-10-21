    @Test
    public void testAliasedFile() throws Exception
    {
        Assume.assumeTrue("OS Supports 8.3 Aliased / Alternate References",OS_ALIAS_SUPPORTED);
        final String path="/subdir/TEXTFI~1.TXT";
        
        Resource resource=context.getResource(path);
        assertNull(resource);
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
