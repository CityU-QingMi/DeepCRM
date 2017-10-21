    public void testSimpleTransform5() throws Exception {
        result.setParse(false);
        result.setStylesheetLocation("XSLTResultTest6.xsl");
        result.execute(mai);

        String out = response.getContentAsString();
        assertTrue(out.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(out.contains("<title>WebWork in Action</title>"));
        assertTrue(out.contains("<author>Patrick and Jason</author>"));
        assertTrue(out.contains("<editions><edition value=\"I\">I</edition><edition value=\"IV\">IV</edition></editions>"));
        assertTrue(out.contains("<book><title/><author/><editions/></book>"));
        assertTrue(out.contains("<title>XWork not in Action</title>"));
        assertTrue(out.contains("<author>Superman</author>"));
        assertTrue(out.contains("<editions><edition value=\"1234\">1234</edition><edition value=\"345\">345</edition><edition value=\"6667\">6667</edition></editions>"));
    }
