    public void testThemeExpansionTokenWithParent() throws Exception {
        // given
        FreemarkerThemeTemplateLoader loader = new FreemarkerThemeTemplateLoader();
        loader.setUIThemeExpansionToken("~~~");

        TemplateEngine engine = mock(TemplateEngine.class);
        Map<String, String> props = new HashMap<String, String>();
        props.put("parent", "foo/foo");
        when(engine.getThemeProps(Matchers.argThat(new IsEqual<Template>(new Template("template", "foo/bar", "text.ftl"))))).thenReturn(props);
        loader.setTemplateEngine(engine);

        TemplateLoader parent = mock(TemplateLoader.class);
        when(parent.findTemplateSource("template/foo/bar/text.ftl")).thenReturn(null);
        when(parent.findTemplateSource("template/foo/foo/text.ftl")).thenReturn(new Object());

        loader.init(parent);

        // when
        Object actual = loader.findTemplateSource("template/~~~foo/bar/text.ftl");

        // then
        assertThat(actual).isNotNull();
    }
