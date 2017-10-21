	public static MessageSource getJstlAwareMessageSource(
			@Nullable ServletContext servletContext, MessageSource messageSource) {

		if (servletContext != null) {
			String jstlInitParam = servletContext.getInitParameter(Config.FMT_LOCALIZATION_CONTEXT);
			if (jstlInitParam != null) {
				// Create a ResourceBundleMessageSource for the specified resource bundle
				// basename in the JSTL context-param in web.xml, wiring it with the given
				// Spring-defined MessageSource as parent.
				ResourceBundleMessageSource jstlBundleWrapper = new ResourceBundleMessageSource();
				jstlBundleWrapper.setBasename(jstlInitParam);
				jstlBundleWrapper.setParentMessageSource(messageSource);
				return jstlBundleWrapper;
			}
		}
		return messageSource;
	}
