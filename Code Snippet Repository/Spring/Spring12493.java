	@Override
	public boolean checkResource(final Locale locale) throws Exception {
		Assert.state(this.renderer != null, "No Renderer set");

		HttpServletRequest servletRequest = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		}

		Request request = new ServletRequest(this.applicationContext, servletRequest, null) {
			@Override
			public Locale getRequestLocale() {
				return locale;
			}
		};

		return this.renderer.isRenderable(getUrl(), request);
	}
