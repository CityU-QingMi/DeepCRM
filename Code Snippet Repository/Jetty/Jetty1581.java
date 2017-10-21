    @Test
    public void testMiddleSpecNoGrouping()
    {
        RegexPathSpec spec = new RegexPathSpec("^/rest/[^/]+/list$");
        assertEquals("Spec.pathSpec","^/rest/[^/]+/list$",spec.getDeclaration());
        assertEquals("Spec.pattern","^/rest/[^/]+/list$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",3,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.MIDDLE_GLOB,spec.group);

        assertMatches(spec,"/rest/api/list");
        assertMatches(spec,"/rest/1.0/list");
        assertMatches(spec,"/rest/2.0/list");
        assertMatches(spec,"/rest/accounts/list");

        assertNotMatches(spec,"/a");
        assertNotMatches(spec,"/aa");
        assertNotMatches(spec,"/aa/bb");
        assertNotMatches(spec,"/rest/admin/delete");
        assertNotMatches(spec,"/rest/list");
    }
