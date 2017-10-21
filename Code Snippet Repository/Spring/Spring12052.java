	@Override
	@Nullable
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			@Nullable Object handler,Exception ex) {

		if (this.resolvers != null) {
			for (HandlerExceptionResolver handlerExceptionResolver : this.resolvers) {
				ModelAndView mav = handlerExceptionResolver.resolveException(request, response, handler, ex);
				if (mav != null) {
					return mav;
				}
			}
		}
		return null;
	}
