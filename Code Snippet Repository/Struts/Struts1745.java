    public void testEncodingForXMLHttpRequest() throws Exception {
        // given
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("X-Requested-With", "XMLHttpRequest");
        req.setCharacterEncoding("UTF-8");
        HttpServletResponse res = new MockHttpServletResponse();

        Dispatcher du = initDispatcher(new HashMap<String, String>() {{
            put(StrutsConstants.STRUTS_I18N_ENCODING, "latin-2");
        }});

        // when
        du.prepare(req, res);

        // then
        assertEquals(req.getCharacterEncoding(), "UTF-8");
    }
