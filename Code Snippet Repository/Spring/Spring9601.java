	@Override
	public void removeAttribute(String name, int scope) {
		if (scope == SCOPE_REQUEST) {
			if (isRequestActive()) {
				this.request.removeAttribute(name);
				removeRequestDestructionCallback(name);
			}
		}
		else {
			HttpSession session = getSession(false);
			if (session != null) {
				this.sessionAttributesToUpdate.remove(name);
				try {
					session.removeAttribute(name);
					// Remove any registered destruction callback as well.
					session.removeAttribute(DESTRUCTION_CALLBACK_NAME_PREFIX + name);
				}
				catch (IllegalStateException ex) {
					// Session invalidated - shouldn't usually happen.
				}
			}
		}
	}
