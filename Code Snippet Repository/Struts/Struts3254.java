    public void testRenderButtonImage() {
        tag.setSrc("http://somesource/image.gif");
        tag.setLabel("alt text");
        tag.setType("image");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<input src='http://somesource/image.gif' type='image' alt='alt text'></input>");
        assertEquals(expected, output);
    }
