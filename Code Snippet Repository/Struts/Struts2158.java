    public void testNoNameDefined() throws Exception {
        String msg = "tag 'text', field 'name': You must specify the i18n key. Example: welcome.header";
        try {
            tag.doStartTag();
            tag.doEndTag();
            fail("Should have thrown a RuntimeException");
        } catch (StrutsException e) {
            assertEquals(msg, e.getMessage());
        }
    }
