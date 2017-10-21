    public void testRenderLabel() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setId("id1");
        tag.setFor("for_");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<label name='name' for='for_' id='id1' class='class1' style='style1' title='title'>val1</label>");
        assertEquals(expected, output);
    }
