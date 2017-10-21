    public void testRenderTextField() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setSize("10");
        tag.setMaxlength("11");
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
        String expected = s("<input type='text' name='name' size='10' maxlength='11' value='val1' tabindex='1' id='id1' class='class1' style='style1' title='title'></input>");
        assertEquals(expected, output);
    }
