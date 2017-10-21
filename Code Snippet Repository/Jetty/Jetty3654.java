    @Test
    public void testResetWithNewSession() throws Exception
    {
        Response response = getResponse();
        Request request = response.getHttpChannel().getRequest();
        
        SessionHandler session_handler = new SessionHandler();
        session_handler.setServer(_server);
        session_handler.setUsingCookies(true);
        session_handler.start();
        request.setSessionHandler(session_handler);
        HttpSession session = request.getSession(true);
        
        assertThat(session,not(nullValue()));
        assertTrue(session.isNew());
        
        HttpField set_cookie = response.getHttpFields().getField(HttpHeader.SET_COOKIE);
        assertThat(set_cookie,not(nullValue()));
        assertThat(set_cookie.getValue(),startsWith("JSESSIONID"));
        assertThat(set_cookie.getValue(),containsString(session.getId()));
        response.setHeader("Some","Header");
        response.addCookie(new Cookie("Some","Cookie"));
        response.getOutputStream().print("X");
        assertThat(response.getHttpFields().size(),is(4));
        
        response.reset();
        
        set_cookie = response.getHttpFields().getField(HttpHeader.SET_COOKIE);
        assertThat(set_cookie,not(nullValue()));
        assertThat(set_cookie.getValue(),startsWith("JSESSIONID"));
        assertThat(set_cookie.getValue(),containsString(session.getId()));
        assertThat(response.getHttpFields().size(),is(2));
        response.getWriter();
    }
