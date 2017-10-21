	protected ModelAndView handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException {

		if (!response.isCommitted()) {
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}
		else if (logger.isErrorEnabled()) {
			logger.error("Async timeout for " + request.getMethod() + " [" + request.getRequestURI() + "]");
		}
		return new ModelAndView();
	}
