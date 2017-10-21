    public void testRenderTextFieldDynamicAttrs() throws Exception {
        UIBean tag = getUIBean();

        applyDynamicAttrs(tag);

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();

        assertDynamicAttrs(output);
    }
