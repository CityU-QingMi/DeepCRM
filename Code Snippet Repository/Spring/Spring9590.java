	@Override
	public Object getSessionMutex() {
		// Enforce presence of a session first to allow listeners to create the mutex attribute
		ExternalContext externalContext = getExternalContext();
		Object session = externalContext.getSession(true);
		Object mutex = externalContext.getSessionMap().get(WebUtils.SESSION_MUTEX_ATTRIBUTE);
		if (mutex == null) {
			mutex = (session != null ? session : externalContext);
		}
		return mutex;
	}
