    public void testRenderFieldErrorWithoutFieldName() {
        this.fieldNames.clear();

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<ul class='errorMessage'><li><span>not good</span></li><li><span>bad</span></li><li><span>bad to the bone</span></li></ul>");
        assertEquals(expected, output);
    }
