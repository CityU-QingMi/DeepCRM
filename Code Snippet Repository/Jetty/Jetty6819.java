    @SuppressWarnings("")
    private <T> T assertIsExtension(String msg, Object obj, Class<T> clazz)
    {
        if (clazz.isAssignableFrom(obj.getClass()))
        {
            return (T)obj;
        }
        Assert.assertEquals("Expected " + msg + " class",clazz.getName(),obj.getClass().getName());
        return null;
    }
