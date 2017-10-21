    public void testRenderAnchor() {
        tag.setName("name_");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id_");
        tag.setCssClass("class");
        tag.setCssStyle("style");
        tag.setTitle("title");
        tag.setHref("http://sometest.com?ab=10");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<a name='name_' id='id_' class='class' style='style' href='http://sometest.com?ab=10' title='title' tabindex='1'></a>");
        assertEquals(expected, output);
    }
