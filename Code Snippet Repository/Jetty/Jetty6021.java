    @Test
    public void ordering() throws Exception
    {
        // The existence of a URLStreamHandler changes the behavior
        assumeThat("URLStreamHandler changes behavior, skip test", URLStreamHandlerUtil.getFactory(), nullValue());

        Enumeration<URL> resources = _loader.getResources("org/acme/clashing.txt");
        assertTrue(resources.hasMoreElements());
        URL resource = resources.nextElement();
        try (InputStream data = resource.openStream())
        {
            assertEquals("correct contents of " + resource, "alpha", IO.toString(data));
        }
        assertTrue(resources.hasMoreElements());
        resource = resources.nextElement();
        try (InputStream data = resource.openStream())
        {
            assertEquals("correct contents of " + resource, "omega", IO.toString(data));
        }
        assertFalse(resources.hasMoreElements());
    }
