    public void testRenderPassword() throws Exception {
        this.showPassword = false;
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


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<input name='name' type='password' size='10' tabindex='1' id='id1' class='class1' style='style1' title='title'></input>");
        assertEquals(expected, output);
    }
