    public void testRenderTokenTag() {
        tag.setName("name");
        tag.setValue("val1");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();


        //token id is random
        String pattern = s("<input type='hidden' name='struts.token.name' value='name'></input><input type='hidden' name='name' value='.*?'></input>");
        assertTrue(Pattern.matches(pattern, output));
    }
