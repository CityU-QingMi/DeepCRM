	private int doGetAttributeScope(String name) {
		if (attributes.get(name) != null)
			return PAGE_SCOPE;

		if (request.getAttribute(name) != null)
			return REQUEST_SCOPE;

		if (session != null) {
		    try {
		        if (session.getAttribute(name) != null)
		            return SESSION_SCOPE;
	        } catch(IllegalStateException ise) {
	            // Session has been invalidated.
		        // Ignore and fall through to application scope.
		    }
		}

		if (context.getAttribute(name) != null)
			return APPLICATION_SCOPE;

		return 0;
	}
