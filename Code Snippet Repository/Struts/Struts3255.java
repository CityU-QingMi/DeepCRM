    public void testRenderButtonImageWithBody() {
        tag.setSrc("http://somesource/image.gif");
        tag.setLabel("alt text");
        tag.setType("image");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        map.clear();
        tag.setType("image");
        tag.addParameter("body", "<span>hey hey hey, here I go now</span>");
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<input src='http://somesource/image.gif' type='image' alt='alt text'><span>hey hey hey, here I go now</span></input>");
        assertEquals(expected, output);
    }
