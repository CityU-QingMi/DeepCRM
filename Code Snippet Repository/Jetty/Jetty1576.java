    @Test
    public void testUriTemplateMatchOrder()
    {
        PathMappings<String> p = new PathMappings<>();

        p.put(new UriTemplatePathSpec("/a/{var}/c"),"endpointA");
        p.put(new UriTemplatePathSpec("/a/b/c"),"endpointB");
        p.put(new UriTemplatePathSpec("/a/{var1}/{var2}"),"endpointC");
        p.put(new UriTemplatePathSpec("/{var1}/d"),"endpointD");
        p.put(new UriTemplatePathSpec("/b/{var2}"),"endpointE");

        // dumpMappings(p);

        assertMatch(p,"/a/b/c","endpointB");
        assertMatch(p,"/a/d/c","endpointA");
        assertMatch(p,"/a/x/y","endpointC");

        assertMatch(p,"/b/d","endpointE");
    }
