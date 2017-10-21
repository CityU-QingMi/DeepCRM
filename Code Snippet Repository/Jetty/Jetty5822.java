    @Test
    public void testEncodeAddPath ()
    throws Exception
    {
        if (data.dir)
        {
            Resource r = data.resource.addPath("foo%/b r");
            Assert.assertThat(r.getURI().toString(),Matchers.endsWith("/foo%25/b%20r"));
        }
    }
