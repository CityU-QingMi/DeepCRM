    @Test
    public void testUncPath() throws Exception
    {
        assumeTrue("Only windows supports UNC paths", OS.IS_WINDOWS);
        assumeFalse("FileResource does not support this test", _class.equals(FileResource.class));
        
        try (Resource base = newResource(URI.create("file:////127.0.0.1/path")))
        {
            Resource resource = base.addPath("WEB-INF/");
            assertThat("getURI()", resource.getURI().toASCIIString(), containsString("path/WEB-INF/"));
            assertThat("isAlias()", resource.isAlias(), is(false));
            assertThat("getAlias()", resource.getAlias(), nullValue());
        }
    }
