    @Test
    public void testFileArg()
    {
        FileArg arg = new FileArg(null,rawFileRef);
        if (expectedUri == null)
        {
            assertThat("URI",arg.uri,nullValue());
        }
        else
        {
            assertThat("URI",arg.uri,is(expectedUri));
        }
        assertThat("Location",arg.location,is(expectedLocation));
    }
