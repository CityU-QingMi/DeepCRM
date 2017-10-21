    protected void executeMimeResult(final String finalLocation) throws PortletException, IOException {
        if (LOG.isDebugEnabled()) LOG.debug("Executing mime result");
        PortletContext ctx = PortletActionContext.getPortletContext();
        PortletRequest req = PortletActionContext.getRequest();
        PortletResponse res = PortletActionContext.getResponse();

		if (StringUtils.isNotEmpty(title) && res instanceof RenderResponse) {
		    ((RenderResponse)res).setTitle(title);
		}
        if (LOG.isDebugEnabled()) LOG.debug("Location: " + finalLocation);
        PortletRequestDispatcher dispatcher;
        if (useDispatcherServlet) {
            req.setAttribute(PortletConstants.DISPATCH_TO, finalLocation);
            dispatcher = ctx.getNamedDispatcher(dispatcherServletName);
            if(dispatcher == null) {
                throw new PortletException("Could not locate dispatcher servlet \"" + dispatcherServletName + "\". Please configure it in your web.xml file");
            }
        } else {
            dispatcher = ctx.getRequestDispatcher(finalLocation);
            if (dispatcher == null) {
                throw new PortletException("Could not locate dispatcher for '" + finalLocation + "'");
            }
        }
        resultHelper.include( dispatcher, contentType, req, res );
    }
