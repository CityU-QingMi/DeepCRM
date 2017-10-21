    public void testGetResources_Multiple() throws IOException {
        Iterator<URL> i = ClassLoaderUtil.getResources("xwork-1.0.dtd", ClassLoaderUtilTest.class, false);
        assertNotNull(i);
        
        assertTrue(i.hasNext());
        URL url = i.next();
        assertTrue(url.toString().endsWith("xwork-1.0.dtd"));
        url = i.next();
        assertTrue(url.toString().endsWith("xwork-1.0.dtd"));
        assertTrue(!i.hasNext());
    }
