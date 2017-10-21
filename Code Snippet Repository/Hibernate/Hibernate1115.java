	private void throwLazyInitializationException(Cause cause, LazyInitializationWork work) {
		final String reason;
		switch ( cause ) {
			case NO_SESSION: {
				reason = "no session and settings disallow loading outside the Session";
				break;
			}
			case CLOSED_SESSION: {
				reason = "session is closed and settings disallow loading outside the Session";
				break;
			}
			case DISCONNECTED_SESSION: {
				reason = "session is disconnected and settings disallow loading outside the Session";
				break;
			}
			case NO_SF_UUID: {
				reason = "could not determine SessionFactory UUId to create temporary Session for loading";
				break;
			}
			default: {
				reason = "<should never get here>";
			}
		}

		final String message = String.format(
				Locale.ROOT,
				"Unable to perform requested lazy initialization [%s.%s] - %s",
				work.getEntityName(),
				work.getAttributeName(),
				reason
		);

		throw new LazyInitializationException( message );
	}
