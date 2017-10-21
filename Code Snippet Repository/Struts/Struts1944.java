    public void testPrependServletContextFalse() {
        view.setLocation("/bar/foo.jsp");
        view.setPrependServletContext(false);
        responseMock.expectAndReturn("encodeRedirectURL", "/bar/foo.jsp", "/bar/foo.jsp");
        responseMock.expect("sendRedirect", C.args(C.eq("/bar/foo.jsp")));

        try {
            view.execute(ai);
            requestMock.verify();
            responseMock.verify();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
