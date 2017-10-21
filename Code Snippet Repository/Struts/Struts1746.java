    public void testSetEncodingIfDiffer() throws Exception {
        // given
        Mock mock = new Mock(HttpServletRequest.class);
        mock.expectAndReturn("getCharacterEncoding", "utf-8");
        mock.expectAndReturn("getHeader", "X-Requested-With", "");
        mock.expectAndReturn("getCharacterEncoding", "utf-8");
        HttpServletRequest req = (HttpServletRequest) mock.proxy();
        HttpServletResponse res = new MockHttpServletResponse();

        Dispatcher du = initDispatcher(new HashMap<String, String>() {{
            put(StrutsConstants.STRUTS_I18N_ENCODING, "utf-8");
        }});


        // when
        du.prepare(req, res);

        // then

        assertEquals(req.getCharacterEncoding(), "utf-8");
        mock.verify();
    }
