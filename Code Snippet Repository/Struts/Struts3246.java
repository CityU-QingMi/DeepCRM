    public void testRenderSelectWithOptions() {
        tag.setList("%{list}");
        tag.setListKey("intField");
        tag.setListValue("stringField");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<select name=''><option value='1'>val</option></select>");
        assertEquals(expected, output);
    }
