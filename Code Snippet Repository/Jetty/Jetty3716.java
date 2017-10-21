    @Test
    public void testAliasedFileAllowed() throws Exception
    {
        Assume.assumeTrue("OS Supports 8.3 Aliased / Alternate References",OS_ALIAS_SUPPORTED);
        try
        {
            allowAliases.set(true);
            final String path="/subdir/TEXTFI~1.TXT";

            Resource resource=context.getResource(path);
            assertNotNull(resource);
            assertEquals(context.getResource("/subdir/TextFile.Long.txt").getURI(),resource.getAlias());
            
            URL url=context.getServletContext().getResource(path);
            assertNotNull(url);
            assertEquals(docroot,new File(url.toURI()).getParentFile().getParentFile());
        }
        finally
        {
            allowAliases.set(false);
        }
        
    }
