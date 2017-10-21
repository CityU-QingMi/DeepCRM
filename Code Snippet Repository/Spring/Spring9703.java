	private static boolean hasWrapper(ServletResponse response) {
		if (response instanceof RelativeRedirectResponseWrapper) {
			return true;
		}
		while (response instanceof HttpServletResponseWrapper) {
			response = ((HttpServletResponseWrapper) response).getResponse();
			if (response instanceof RelativeRedirectResponseWrapper) {
				return true;
			}
		}
		return false;
	}
