    public void testFullUrlRedirectWithSpaces() {
        view.setLocation("http://localhost/bar/foo some.pdf");
        responseMock.expectAndReturn("encodeRedirectURL", C.args(C.eq("http://localhost/bar/foo some.pdf")), "http://localhost/bar/foo some.pdf");
        responseMock.expect("sendRedirect", C.args(C.eq("http://localhost/bar/foo some.pdf")));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
