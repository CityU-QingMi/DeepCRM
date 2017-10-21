		@Override
		public ServletAppender build() {
			final String name = getName();
			if (name == null) {
				LOGGER.error("No name provided for ServletAppender");
			}
			final ServletContext servletContext = WebLoggerContextUtils.getServletContext();
			if (servletContext == null) {
				LOGGER.error("No servlet context is available");
				return null;
			}
			Layout<? extends Serializable> layout = getLayout();
			if (layout == null) {
				layout = PatternLayout.createDefaultLayout();
			} else if (!(layout instanceof AbstractStringLayout)) {
				LOGGER.error("Layout must be a StringLayout to log to ServletContext");
				return null;
			}
			return new ServletAppender(name, layout, getFilter(), servletContext, isIgnoreExceptions(), logThrowables);
		}
