	private void doForward(String relativeUrlPath) throws ServletException,
			IOException {

		// JSP.4.5 If the buffer was flushed, throw IllegalStateException
		try {
			out.clear();
		} catch (IOException ex) {
			IllegalStateException ise = new IllegalStateException(Localizer
					.getMessage("jsp.error.attempt_to_clear_flushed_buffer"));
			ise.initCause(ex);
			throw ise;
		}

		// Make sure that the response object is not the wrapper for include
		while (response instanceof ServletResponseWrapperInclude) {
			response = ((ServletResponseWrapperInclude) response).getResponse();
		}

		final String path = getAbsolutePathRelativeToContext(relativeUrlPath);
		String includeUri = (String) request
				.getAttribute(Constants.INC_SERVLET_PATH);

		if (includeUri != null)
			request.removeAttribute(Constants.INC_SERVLET_PATH);
		try {
			context.getRequestDispatcher(path).forward(request, response);
		} finally {
			if (includeUri != null)
				request.setAttribute(Constants.INC_SERVLET_PATH, includeUri);
		}
	}
