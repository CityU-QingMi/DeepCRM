    public void testRenderTextFieldCommonAttrs() throws Exception {
        UIBean tag = getUIBean();


        applyCommonAttrs(tag);

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();

        assertCommonAttrs(output);
    }
