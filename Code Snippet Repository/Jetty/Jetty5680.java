    static void fromString(String test,String s,String field,String expected,boolean thrown) throws Exception
    {
        MultiMap<String> values=new MultiMap<>();
        try
        {
            UrlEncoded.decodeUtf8To(s, 0, s.length(), values);
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
