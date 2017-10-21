     public void testRenderTextAreaDefaults() {
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setReadonly("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<textarea name='' cols='' rows='' tabindex='1' id='id1' class='class1' style='style1' title='title'>val1</textarea>");
        assertEquals(expected, output);
    }
