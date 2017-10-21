    @Test(expected = IllegalArgumentException.class)
    public void testBogusFilename() throws Exception
    {
        if (OS.IS_UNIX)
        {
            // A windows path is invalid under unix
            newResource(new URI("file://Z:/:"));
        }
        else if (OS.IS_WINDOWS)
        {
            // "CON" is a reserved name under windows
            newResource(new URI("file://CON"));
        }
        else
        {
            assumeFalse("Unknown OS type",false);
        }
    }
