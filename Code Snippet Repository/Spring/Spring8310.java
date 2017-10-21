	private MockHttpSession httpSession(MockHttpServletRequest request, final String sessionid) {
		MockHttpSession session;
		synchronized (this.sessions) {
			session = this.sessions.get(sessionid);
			if (session == null) {
				session = new HtmlUnitMockHttpSession(request, sessionid);
				session.setNew(true);
				synchronized (this.sessions) {
					this.sessions.put(sessionid, session);
				}
				addSessionCookie(request, sessionid);
			}
			else {
				session.setNew(false);
			}
		}
		return session;
	}
