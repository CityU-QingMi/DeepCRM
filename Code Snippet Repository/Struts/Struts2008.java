    public void testDynamicAttributesInTheme() throws Exception {
        File file = new File(FreeMarkerResultTest.class.getResource("customTextField.ftl").toURI());
        EasyMock.expect(servletContext.getRealPath("/tutorial/org/apache/struts2/views/freemarker/customTextField.ftl")).andReturn(file.getAbsolutePath());

        file = new File(ClassLoaderUtil.getResource("template/test/text.ftl", getClass()).toURI());
        EasyMock.expect(servletContext.getRealPath("/template/test/text.ftl")).andReturn(file.getAbsolutePath());

        EasyMock.replay(servletContext);

        init();

        request.setRequestURI("/tutorial/test8.action");
        ActionMapping mapping = container.getInstance(ActionMapper.class).getMapping(request, configurationManager);
        dispatcher.serviceAction(request, response, mapping);
        String expected = "<input type=\"text\"autofocus=\"autofocus\"/>";
        assertEquals(expected, stringWriter.toString());
    }
