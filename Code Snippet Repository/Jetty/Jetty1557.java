    @Test
    public void testPathMappingsOnlyMatchOnDirectoryNames() throws Exception
    {
        String spec = "/xyz/*";

        assertMatch(spec, "/xyz");
        assertMatch(spec, "/xyz/");
        assertMatch(spec, "/xyz/123");
        assertMatch(spec, "/xyz/123/");
        assertMatch(spec, "/xyz/123.txt");
        assertNotMatch(spec, "/xyz123");
        assertNotMatch(spec, "/xyz123;jessionid=99");
        assertNotMatch(spec, "/xyz123/");
        assertNotMatch(spec, "/xyz123/456");
        assertNotMatch(spec, "/xyz.123");
        assertNotMatch(spec, "/xyz;123"); // as if the ; was encoded and part of the path
        assertNotMatch(spec, "/xyz?123"); // as if the ? was encoded and part of the path
    }
