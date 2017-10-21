    public void testRenderButtonWithBody() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");
        tag.setLabel("some label");
        tag.setType("button");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);

        tag.addParameter("body", "<span>hey hey hey, here I go now</span>");
        map.clear();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<button name='name' type='submit' value='val1' tabindex='1' id='id1' class='class1' style='style1'><span>hey hey hey, here I go now</span></button>");
        assertEquals(expected, output);
    }
