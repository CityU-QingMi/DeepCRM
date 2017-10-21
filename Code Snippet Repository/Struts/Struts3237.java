    public void testRenderForm() {
        tag.setName("name_");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id_");
        tag.setCssClass("class_");
        tag.setCssStyle("style_");
        tag.setTitle("title");
        tag.setAcceptcharset("charset_");
        tag.setAction("action_");
        tag.setOnsubmit("submit");
        tag.setOnreset("reset");
        tag.setTarget("target_");
        tag.setEnctype("enc");
        tag.setMethod("post");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<form name='name_' id='id_' onsubmit='submit' onreset='reset' target='target_' enctype='enc' class='class_' style='style_' title='title' accept-charset='charset_' method='post'></form>");
        assertEquals(expected, output);
    }
