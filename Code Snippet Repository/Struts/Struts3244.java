    public void testRenderSelect() {
        tag.setName("name_");
        tag.setSize("10");
        tag.setDisabled("true");
        tag.setMultiple("true");
        tag.setTabindex("1");
        tag.setId("id_");
        tag.setCssClass("class");
        tag.setCssStyle("style");
        tag.setTitle("title");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<select name='name_' size='10' tabindex='1' id='id_' class='class' style='style' title='title'></select>");
        assertEquals(expected, output);
    }
