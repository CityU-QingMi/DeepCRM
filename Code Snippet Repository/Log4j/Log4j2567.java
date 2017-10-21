    @Test
    public void testFileExistsWithPlusCharactersInName() throws Exception {
        final String config = "target/test-classes/log4j+config+with+plus+characters.xml";
        final File file = new File(config);
        assertEquals(LOG4J_CONFIG_WITH_PLUS, file.getName());
        assertTrue("file exists", file.exists());
        //
        final URI uri1 = new URI(config);
        assertNull(uri1.getScheme());
        //
        final URI uri2 = new File(uri1.getPath()).toURI();
        assertNotNull(uri2);
        assertTrue("URI \"" + uri2 + "\" does not end with \"" + LOG4J_CONFIG_WITH_PLUS + "\"", uri2.toString()
                .endsWith(LOG4J_CONFIG_WITH_PLUS));
        //
        final String fileName = uri2.toURL().getFile();
        assertTrue("File name \"" + fileName + "\" does not end with \"" + LOG4J_CONFIG_WITH_PLUS + "\"",
                fileName.endsWith(LOG4J_CONFIG_WITH_PLUS));
    }
