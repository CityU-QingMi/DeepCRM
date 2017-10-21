    @Test
    public void testTwoVarComplexInnerPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a/{var1}/c/{var2}/e");
        assertEquals("Spec.pathSpec","/a/{var1}/c/{var2}/e",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/([^/]+)/c/([^/]+)/e$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",5,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.MIDDLE_GLOB,spec.getGroup());

        assertDetectedVars(spec,"var1","var2");

        assertMatches(spec,"/a/b/c/d/e");
        assertNotMatches(spec,"/a/bc/d/e");
        assertNotMatches(spec,"/a/b/d/e");
        assertNotMatches(spec,"/a/b//d/e");

        Map<String, String> mapped = spec.getPathParams("/a/b/c/d/e");
        assertThat("Spec.pathParams",mapped,notNullValue());
        assertThat("Spec.pathParams.size",mapped.size(),is(2));
        assertEquals("Spec.pathParams[var1]","b",mapped.get("var1"));
        assertEquals("Spec.pathParams[var2]","d",mapped.get("var2"));
    }
