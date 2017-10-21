    static void fromInputStream(String test, byte[] b,String field, String expected,boolean thrown) throws Exception
    {
        InputStream is=new ByteArrayInputStream(b);
        MultiMap<String> values=new MultiMap<>();
        try
        {
            UrlEncoded.decodeUtf8To(is, values, 1000000,-1);
            if (thrown)
                Assert.fail();
            Assert.assertEquals(test, expected, values.getString(field));
        }
        catch (Exception e)
        {
            if (!thrown)
                throw e;
            LOG.ignore(e);
        }
    }
