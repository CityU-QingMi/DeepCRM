	@Nullable
	protected final HttpSession getSession(boolean allowCreate) {
		if (isRequestActive()) {
			HttpSession session = this.request.getSession(allowCreate);
			this.session = session;
			return session;
		}
		else {
			// Access through stored session reference, if any...
			HttpSession session = this.session;
			if (session == null) {
				if (allowCreate) {
					throw new IllegalStateException(
							"No session found and request already completed - cannot create new session!");
				}
				else {
					session = this.request.getSession(false);
					this.session = session;
				}
			}
			return session;
		}
	}
