    @Test
    public void testOneVarPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a/{foo}");
        assertEquals("Spec.pathSpec","/a/{foo}",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/([^/]+)$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",2,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.PREFIX_GLOB,spec.getGroup());

        assertDetectedVars(spec,"foo");

        assertMatches(spec,"/a/b");
        assertNotMatches(spec,"/a/");
        assertNotMatches(spec,"/a");

        Map<String, String> mapped = spec.getPathParams("/a/b");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(1));
        assertEquals("Spec.pathParams[foo]","b",mapped.get("foo"));
    }
