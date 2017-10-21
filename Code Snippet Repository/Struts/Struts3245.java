    public void testRenderSelectWithHeader() {
        tag.setList("%{{'key0', 'key1'}}");
        tag.setHeaderKey("%{'key0'}");
        tag.setHeaderValue("%{'val'}");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<select name=''><option value='key0'>val</option></select>");
        assertEquals(expected, output);
    }
