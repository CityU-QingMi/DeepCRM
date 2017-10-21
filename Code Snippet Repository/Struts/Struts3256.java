    public void testRenderTextArea() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setReadonly("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");
        tag.setRows("1");
        tag.setCols("2");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<textarea name='name' cols='2' rows='1' tabindex='1' id='id1' class='class1' style='style1' title='title'>val1</textarea>");
        assertEquals(expected, output);
    }
