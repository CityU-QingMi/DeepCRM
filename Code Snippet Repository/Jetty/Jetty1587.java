    private void assertBadServletPathSpec(String pathSpec)
    {
        try
        {
            new ServletPathSpec(pathSpec);
            fail("Expected IllegalArgumentException for a bad servlet pathspec on: " + pathSpec);
        }
        catch (IllegalArgumentException e)
        {
            // expected path
            System.out.println(e);
        }
    }
