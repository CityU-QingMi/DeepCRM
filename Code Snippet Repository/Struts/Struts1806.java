    public void testAllowedMethodNames() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();

        assertEquals("", mapper.cleanupMethodName(""));
        assertEquals("test", mapper.cleanupMethodName("test"));
        assertEquals("test_method", mapper.cleanupMethodName("test_method"));
        assertEquals("_test", mapper.cleanupMethodName("_test"));
        assertEquals("test1", mapper.cleanupMethodName("test1"));

        assertEquals(mapper.defaultMethodName, mapper.cleanupMethodName("2test"));
        assertEquals(mapper.defaultMethodName, mapper.cleanupMethodName("%{exp}"));
        assertEquals(mapper.defaultMethodName, mapper.cleanupMethodName("${%{foo}}"));
        assertEquals(mapper.defaultMethodName, mapper.cleanupMethodName("${#foo='method',#foo}"));
    }
