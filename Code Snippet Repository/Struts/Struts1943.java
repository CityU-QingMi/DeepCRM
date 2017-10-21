    public void testAbsoluteRedirectAnchor() {
        view.setLocation("/bar/foo.jsp");
        view.setAnchor("fragment");
        responseMock.expectAndReturn("encodeRedirectURL", "/context/bar/foo.jsp#fragment", "/context/bar/foo.jsp#fragment");
        responseMock.expect("sendRedirect", C.args(C.eq("/context/bar/foo.jsp#fragment")));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
