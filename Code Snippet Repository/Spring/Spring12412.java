	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {

		String newTheme = request.getParameter(this.paramName);
		if (newTheme != null) {
			ThemeResolver themeResolver = RequestContextUtils.getThemeResolver(request);
			if (themeResolver == null) {
				throw new IllegalStateException("No ThemeResolver found: not in a DispatcherServlet request?");
			}
			themeResolver.setThemeName(request, response, newTheme);
		}
		// Proceed in any case.
		return true;
	}
