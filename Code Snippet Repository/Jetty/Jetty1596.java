    @Test
    public void testTwoVarComplexOuterPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/{var1}/b/{var2}/{var3}");
        assertEquals("Spec.pathSpec","/{var1}/b/{var2}/{var3}",spec.getDeclaration());
        assertEquals("Spec.pattern","^/([^/]+)/b/([^/]+)/([^/]+)$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",4,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.MIDDLE_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var1","var2","var3");

        assertMatches(spec,"/a/b/c/d");
        assertNotMatches(spec,"/a/bc/d/e");
        assertNotMatches(spec,"/a/c/d/e");
        assertNotMatches(spec,"/a//d/e");

        Map<String, String> mapped = spec.getPathParams("/a/b/c/d");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(3));
        assertEquals("Spec.pathParams[var1]","a",mapped.get("var1"));
        assertEquals("Spec.pathParams[var2]","c",mapped.get("var2"));
        assertEquals("Spec.pathParams[var3]","d",mapped.get("var3"));
    }
