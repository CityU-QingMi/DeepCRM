    public void testRenderButtonWithLabel() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");
        tag.setLabel("Just as soon as I belong, than its time I disappear");
        tag.setType("button");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<button name='name' type='submit' value='val1' tabindex='1' id='id1' class='class1' style='style1'>Just as soon as I belong, than its time I disappear</button>");
        assertEquals(expected, output);
    }
