    @Test
    public void testVarOnlyPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/{var1}");
        assertEquals("Spec.pathSpec","/{var1}",spec.getDeclaration());
        assertEquals("Spec.pattern","^/([^/]+)$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",1,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.PREFIX_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var1");

        assertMatches(spec,"/a");
        assertNotMatches(spec,"/");
        assertNotMatches(spec,"/a/b");
        assertNotMatches(spec,"/a/b/c");

        Map<String, String> mapped = spec.getPathParams("/a");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(1));
        assertEquals("Spec.pathParams[var1]","a",mapped.get("var1"));
    }
