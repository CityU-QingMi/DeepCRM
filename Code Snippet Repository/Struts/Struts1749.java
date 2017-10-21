    public void testPrepareMultipartRequestAllAllowedCharacters() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        req.setMethod("post");
        req.setContentType("multipart/form-data; boundary=01=23a.bC:D((e)d'z?p+o_r,e-");
        Dispatcher du = initDispatcher(Collections.<String, String>emptyMap());
        du.prepare(req, res);
        HttpServletRequest wrapped = du.wrapRequest(req);

        assertTrue(wrapped instanceof MultiPartRequestWrapper);
    }
