    @Test
    public void testOneVarSuffixPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/{var}/b/c");
        assertEquals("Spec.pathSpec","/{var}/b/c",spec.getDeclaration());
        assertEquals("Spec.pattern","^/([^/]+)/b/c$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",3,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.SUFFIX_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var");

        assertMatches(spec,"/a/b/c");
        assertMatches(spec,"/az/b/c");
        assertMatches(spec,"/hello+world/b/c");
        assertNotMatches(spec,"/a/bc");
        assertNotMatches(spec,"/a/b/");
        assertNotMatches(spec,"/a/b");

        Map<String, String> mapped = spec.getPathParams("/a/b/c");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(1));
        assertEquals("Spec.pathParams[var]","a",mapped.get("var"));
    }
