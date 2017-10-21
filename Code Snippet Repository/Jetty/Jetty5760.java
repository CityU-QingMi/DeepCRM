    @Test
    public void testClassPathResourceFile() throws Exception
    {
        final String fileName="resource.txt";
        final String classPathName="/"+fileName;

        // Will locate a resource in the class path
        Resource resource=Resource.newClassPathResource(classPathName);

        // A class path cannot be a directory
        assertFalse("Class path must be a directory.",resource.isDirectory());

        assertTrue(resource!=null);

        File file=resource.getFile();

        assertEquals("File name from class path is not equal.",fileName,file.getName());
        assertTrue("File returned from class path should be a file.",file.isFile());

        // A class path must exist
        assertTrue("Class path resource does not exist.",resource.exists());
    }
