    @Test
    public void testClassPathResourceDirectory() throws Exception
    {
        final String classPathName="/";

        Resource resource=Resource.newClassPathResource(classPathName);

        // A class path must be a directory
        assertTrue("Class path must be a directory.",resource.isDirectory());

        assertTrue("Class path returned file must be a directory.",resource.getFile().isDirectory());

        // A class path must exist
        assertTrue("Class path resource does not exist.",resource.exists());
    }
