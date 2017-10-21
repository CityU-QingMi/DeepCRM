    @Test
    public void testBadPathSpec()
    {
        try
        {
            new UriTemplatePathSpec(this.pathSpec);
            fail("Expected IllegalArgumentException for a bad PathParam pathspec on: " + pathSpec);
        }
        catch (IllegalArgumentException e)
        {
            // expected path
            System.out.println(e.getMessage());
        }
    }
