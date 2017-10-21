	private void doRemoveAttribute(String name) {
	    removeAttribute(name, PAGE_SCOPE);
	    removeAttribute(name, REQUEST_SCOPE);
	    if( session != null ) {
	        try {
	            removeAttribute(name, SESSION_SCOPE);
	        } catch(IllegalStateException ise) {
	            // Session has been invalidated.
	            // Ignore and fall throw to application scope.
	        }
	    }
	    removeAttribute(name, APPLICATION_SCOPE);
	}
