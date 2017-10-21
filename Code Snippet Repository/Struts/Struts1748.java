    public void testPrepareMultipartRequest() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        req.setMethod("post");
        req.setContentType("multipart/form-data; boundary=asdcvb345asd");
        Dispatcher du = initDispatcher(Collections.<String, String>emptyMap());
        du.prepare(req, res);
        HttpServletRequest wrapped = du.wrapRequest(req);

        assertTrue(wrapped instanceof MultiPartRequestWrapper);
    }
