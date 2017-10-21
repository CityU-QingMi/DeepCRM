    public void testFullUrlRedirect() {
        view.setLocation("http://localhost/bar/foo.jsp");
        responseMock.expectAndReturn("encodeRedirectURL", C.args(C.eq("http://localhost/bar/foo.jsp")), "http://localhost/bar/foo.jsp");
        responseMock.expect("sendRedirect", C.args(C.eq("http://localhost/bar/foo.jsp")));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
