	public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if(request instanceof PortletServletRequest && response instanceof PortletServletResponse) {
			PortletRequest req = ((PortletServletRequest)request).getPortletRequest();
			PortletResponse resp = ((PortletServletResponse)response).getPortletResponse();
			if(req instanceof RenderRequest && resp instanceof RenderResponse) {
				try {
					portletRequestDispatcher.include((RenderRequest)req, (RenderResponse)resp);
				}
				catch(PortletException e) {
					throw new ServletException(e);
				}
			}
			else {
				throw new IllegalStateException("Can only be invoked in the render phase");
			}
		}
		else {
			throw new IllegalStateException("Can only be invoked in a portlet");
		}
	}
