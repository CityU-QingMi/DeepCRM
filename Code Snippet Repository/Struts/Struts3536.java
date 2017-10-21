    public void testRenderUrlWithNamespace() throws Exception {
        // given
        PortletUrlRenderer renderer = new PortletUrlRenderer();
        UrlProvider component = new URL(stack, request, response).getUrlProvider();
        Writer writer = new StringWriter();

        // when
        renderer.renderUrl(writer, component);

        // then
        assertTrue("/portlettest".equals(component.getNamespace()));
    }
