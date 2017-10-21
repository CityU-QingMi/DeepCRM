    public void testRenderActionErrorWithoutCssClass() {
        tag.setCssStyle("style");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<ul style='style' class='errorMessage'><li><span>this clas is bad</span></li><li><span>baaaaad</span></li></ul>");
        assertEquals(expected, output);
    }
