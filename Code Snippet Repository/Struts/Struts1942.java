    public void testAbsoluteRedirect303() {
        view.setLocation("/bar/foo.jsp");
        view.setStatusCode(303);
        responseMock.expectAndReturn("encodeRedirectURL", "/context/bar/foo.jsp", "/context/bar/foo.jsp");
        responseMock.expect("setStatus", C.args(C.eq(SC_SEE_OTHER)));
        responseMock.expect("setHeader", C.args(C.eq("Location"), C.eq("/context/bar/foo.jsp")));
        StringWriter writer = new StringWriter();
        responseMock.matchAndReturn("getWriter", new PrintWriter(writer));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("/context/bar/foo.jsp", writer.toString());
    }
