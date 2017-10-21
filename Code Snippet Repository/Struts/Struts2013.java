    public void testThemeExpansionToken() throws Exception {
        // given
        FreemarkerThemeTemplateLoader loader = new FreemarkerThemeTemplateLoader();
        loader.setUIThemeExpansionToken("~~~");

        TemplateEngine engine = mock(TemplateEngine.class);
        loader.setTemplateEngine(engine);

        TemplateLoader parent = mock(TemplateLoader.class);
        when(parent.findTemplateSource("template/foo/bar/text.ftl")).thenReturn(new Object());

        loader.init(parent);

        // when
        Object actual = loader.findTemplateSource("template/~~~foo/bar/text.ftl");

        // then
        assertThat(actual).isNotNull();
    }
