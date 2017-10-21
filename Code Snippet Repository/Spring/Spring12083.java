	@Override
	@Nullable
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			@Nullable Object handler, Exception ex) {

		try {
			if (ex instanceof ResponseStatusException) {
				return resolveResponseStatusException((ResponseStatusException) ex, request, response, handler);
			}

			ResponseStatus status = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
			if (status != null) {
				return resolveResponseStatus(status, request, response, handler, ex);
			}

			if (ex.getCause() instanceof Exception) {
				ex = (Exception) ex.getCause();
				return doResolveException(request, response, handler, ex);
			}
		}
		catch (Exception resolveEx) {
			logger.warn("Handling of @ResponseStatus resulted in Exception", resolveEx);
		}
		return null;
	}
