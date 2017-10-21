    @Test
    public void testMiddleVarPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a/{var}/c");
        assertEquals("Spec.pathSpec","/a/{var}/c",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/([^/]+)/c$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",3,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.MIDDLE_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var");

        assertMatches(spec,"/a/b/c");
        assertMatches(spec,"/a/zz/c");
        assertMatches(spec,"/a/hello+world/c");
        assertNotMatches(spec,"/a/bc");
        assertNotMatches(spec,"/a/b/");
        assertNotMatches(spec,"/a/b");

        Map<String, String> mapped = spec.getPathParams("/a/b/c");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(1));
        assertEquals("Spec.pathParams[var]","b",mapped.get("var"));
    }
