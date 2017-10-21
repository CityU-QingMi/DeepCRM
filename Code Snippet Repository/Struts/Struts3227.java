    public void testRenderScriptingEvents() {
        tag.setName("name_");
        tag.setOnclick("alert('click')");
        tag.setOnchange("alert('change)");
        tag.setOnfocus("alert('focus')");
        tag.setOnselect("alert('select')");
        tag.setOndblclick("alert('dbclick')");
        tag.setOnkeydown("alert('keydown')");
        tag.setOnkeypress("alert('keypress')");
        tag.setHref("http://sometest.com?ab=10");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = "<a name=\"name_\" id=\"name_\" href=\"http://sometest.com?ab=10\" onclick=\"alert('click')\" " +
                "ondblclick=\"alert('dbclick')\" onfocus=\"alert('focus')\" onkeypress=\"alert('keypress')\" " +
                "onkeydown=\"alert('keydown')\" onselect=\"alert('select')\" onchange=\"alert('change)\"></a>";
        assertEquals(expected, output);
    }
