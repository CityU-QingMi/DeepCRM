    public void testRenderActionErrorNoErrors() {
        this.errors.clear();
        this.fieldNames.clear();

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        assertEquals("", output);
    }
