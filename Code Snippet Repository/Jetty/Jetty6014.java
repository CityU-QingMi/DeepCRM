    public void assertCantLoadClass(String clazz)
    {
        try
        {
            assertThat("Can't Load Class ["+clazz+"]", _loader.loadClass(clazz), nullValue());
        }
        catch (ClassNotFoundException e)
        {
            // Valid path
        }
    }
