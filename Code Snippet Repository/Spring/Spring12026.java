	@Override
	@Nullable
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			@Nullable Object handler, Exception ex) {

		if (shouldApplyTo(request, handler)) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Resolving exception from handler [" + handler + "]: " + ex);
			}
			prepareResponse(ex, response);
			ModelAndView result = doResolveException(request, response, handler, ex);
			if (result != null) {
				logException(ex, request);
			}
			return result;
		}
		else {
			return null;
		}
	}
