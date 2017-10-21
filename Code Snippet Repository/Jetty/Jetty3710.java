    @Test
    public void testBadPath() throws Exception
    {
        final String path="bad";
        try
        {
            context.getResource(path);
            fail("Expected " + MalformedURLException.class);
        }
        catch(MalformedURLException e)
        {
        }
        
        try
        {
            context.getServletContext().getResource(path);
            fail("Expected " + MalformedURLException.class);
        }
        catch(MalformedURLException e)
        {
        }
    }
