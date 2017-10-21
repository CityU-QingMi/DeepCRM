	private Object doFindAttribute(String name) {

		Object o = attributes.get(name);
		if (o != null)
			return o;

		o = request.getAttribute(name);
		if (o != null)
			return o;

		if (session != null) {
		    try {
		        o = session.getAttribute(name);
		    } catch(IllegalStateException ise) {
		        // Session has been invalidated.
		        // Ignore and fall through to application scope.
	        }
			if (o != null)
				return o;
		}

		return context.getAttribute(name);
	}
