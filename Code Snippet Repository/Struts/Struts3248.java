    public void testRenderSelectWithOptionSelected() {
        tag.setList("%{list}");
        tag.setListKey("intField");
        tag.setListValue("stringField");
        tag.setValue("%{'1'}");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<select name='' value='1'><option value='1' selected='selected'>val</option></select>");
        assertEquals(expected, output);
    }
