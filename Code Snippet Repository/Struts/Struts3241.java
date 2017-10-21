    public void testRenderPasswordShowIt() throws Exception {
        this.showPassword = true;
        super.setUp();
        this.tag = new Password(stack, request, response);

        tag.setName("name");
        tag.setValue("val1");
        tag.setSize("10");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");
        tag.setShowPassword("%{'true'}");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<input value='val1' name='name' type='password' size='10' tabindex='1' id='id1' class='class1' style='style1' title='title'></input>");
        assertEquals(expected, output);
    }
