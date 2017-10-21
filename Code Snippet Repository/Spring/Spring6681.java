	@Override
	protected Session getSession(Connection con, Integer mode) throws JMSException {
		LinkedList<Session> sessionList;
		synchronized (this.cachedSessions) {
			sessionList = this.cachedSessions.get(mode);
			if (sessionList == null) {
				sessionList = new LinkedList<>();
				this.cachedSessions.put(mode, sessionList);
			}
		}
		Session session = null;
		synchronized (sessionList) {
			if (!sessionList.isEmpty()) {
				session = sessionList.removeFirst();
			}
		}
		if (session != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Found cached JMS Session for mode " + mode + ": " +
						(session instanceof SessionProxy ? ((SessionProxy) session).getTargetSession() : session));
			}
		}
		else {
			Session targetSession = createSession(con, mode);
			if (logger.isDebugEnabled()) {
				logger.debug("Registering cached JMS Session for mode " + mode + ": " + targetSession);
			}
			session = getCachedSessionProxy(targetSession, sessionList);
		}
		return session;
	}
