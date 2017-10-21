    @Test
    public void testClassPathResourceClassRelative()
    {
        final String classPathName="Resource.class";

        try(Resource resource=Resource.newClassPathResource(classPathName);)
        {
            // A class path cannot be a directory
            assertFalse("Class path cannot be a directory.",resource.isDirectory());

            // A class path must exist
            assertTrue("Class path resource does not exist.",resource.exists());
        }
    }
