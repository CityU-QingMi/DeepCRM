    @Test
    public void testTwoVarPrefixPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a/{var1}/{var2}");
        assertEquals("Spec.pathSpec","/a/{var1}/{var2}",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/([^/]+)/([^/]+)$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",3,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.PREFIX_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var1","var2");

        assertMatches(spec,"/a/b/c");
        assertNotMatches(spec,"/a/bc");
        assertNotMatches(spec,"/a/b/");
        assertNotMatches(spec,"/a/b");

        Map<String, String> mapped = spec.getPathParams("/a/b/c");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(2));
        assertEquals("Spec.pathParams[var1]","b",mapped.get("var1"));
        assertEquals("Spec.pathParams[var2]","c",mapped.get("var2"));
    }
