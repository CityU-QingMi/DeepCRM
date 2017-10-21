    public void testSetExtension() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setExtensions("");
        assertNull(mapper.extensions);
        mapper.setExtensions(null);
        assertNull(mapper.extensions);

        mapper.setExtensions(",xml");
        assertEquals(Arrays.asList("", "xml"), mapper.extensions);

        mapper.setExtensions("html,xml,");
        assertEquals(Arrays.asList("html", "xml", ""), mapper.extensions);

        mapper.setExtensions("html,,xml");
        assertEquals(Arrays.asList("html", "", "xml"), mapper.extensions);

        mapper.setExtensions("xml");
        assertEquals(Arrays.asList("xml"), mapper.extensions);

        mapper.setExtensions(",");
        assertEquals(Arrays.asList(""), mapper.extensions);


    }
