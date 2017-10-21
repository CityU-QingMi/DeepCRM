	private void executeRegularServletResult(String finalLocation, ActionInvocation actionInvocation)
			throws ServletException, IOException {
		ServletContext ctx = ServletActionContext.getServletContext();
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		try {
			ctx.getRequestDispatcher(finalLocation).include(req, res);
		} catch (ServletException e) {
			LOG.error("ServletException including " + finalLocation, e);
			throw e;
		} catch (IOException e) {
			LOG.error("IOException while including result '" + finalLocation + "'", e);
			throw e;
		}
	}
