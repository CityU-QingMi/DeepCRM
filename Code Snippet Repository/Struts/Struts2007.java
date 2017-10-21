    public void testManualListInTemplate() throws Exception {
        File file = new File(FreeMarkerResultTest.class.getResource("manual-list.ftl").toURI());
        EasyMock.expect(servletContext.getRealPath("/tutorial/org/apache/struts2/views/freemarker/manual-list.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/simple/radiomap.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/simple/radiomap.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/simple/css.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/simple/css.ftl")).andReturn(file.getAbsolutePath());
        EasyMock.expect(servletContext.getRealPath("/template/~~~simple/css.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/simple/scripting-events.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/simple/scripting-events.ftl")).andReturn(file.getAbsolutePath());
        EasyMock.expect(servletContext.getRealPath("/template/~~~simple/scripting-events.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/simple/common-attributes.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/simple/common-attributes.ftl")).andReturn(file.getAbsolutePath());
        EasyMock.expect(servletContext.getRealPath("/template/~~~simple/common-attributes.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/simple/dynamic-attributes.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/simple/dynamic-attributes.ftl")).andReturn(file.getAbsolutePath());
        EasyMock.expect(servletContext.getRealPath("/template/~~~simple/dynamic-attributes.ftl")).andReturn(file.getAbsolutePath());

        EasyMock.replay(servletContext);

        init();

        request.setRequestURI("/tutorial/test7.action");
        ActionMapping mapping = container.getInstance(ActionMapper.class).getMapping(request, configurationManager);
        dispatcher.serviceAction(request, response, mapping);
        String expected = "<input type=\"radio\" name=\"client\" id=\"client_foo\" value=\"foo\"/><label for=\"client_foo\">foo</label>\n"
                + "<input type=\"radio\" name=\"client\" id=\"client_bar\" value=\"bar\"/><label for=\"client_bar\">bar</label>\n"
                + "\n"
                + "<input type=\"radio\" name=\"car\" id=\"carford\" value=\"ford\"/><label for=\"carford\">Ford Motor Co</label>\n"
                + "<input type=\"radio\" name=\"car\" id=\"cartoyota\" value=\"toyota\"/><label for=\"cartoyota\">Toyota</label>\n";
        assertEquals(normalize(expected), normalize(stringWriter.toString()));
    }
