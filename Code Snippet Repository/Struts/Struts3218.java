    public void testRenderTextFieldScriptingAttrs() throws Exception {
        UIBean tag = getUIBean();

        applyScriptingAttrs(tag);

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();

        assertScriptingAttrs(output);
    }
