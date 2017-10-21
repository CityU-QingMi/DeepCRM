    public void testFullUrlRedirectWithParams() {
        view.setLocation("http://localhost/bar/foo.action?param=1&param 2=3");
        responseMock.expectAndReturn("encodeRedirectURL", C.args(C.eq("http://localhost/bar/foo.action?param=1&param 2=3")), "http://localhost/bar/foo.action?param=1&param 2=3");
        responseMock.expect("sendRedirect", C.args(C.eq("http://localhost/bar/foo.action?param=1&param 2=3")));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
