	public static JspApplicationContextImpl getInstance(ServletContext context) {
		if (context == null) {
			throw new IllegalArgumentException("ServletContext was null");
		}
		JspApplicationContextImpl impl = (JspApplicationContextImpl) context
				.getAttribute(KEY);
		if (impl == null) {
			impl = new JspApplicationContextImpl();
			context.setAttribute(KEY, impl);
		}
		return impl;
	}
