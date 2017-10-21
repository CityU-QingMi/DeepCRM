	@Override
	protected void handleSuccess(HttpServletRequest request, HttpServletResponse response,
			UpgradeInfo upgradeInfo, TyrusUpgradeResponse upgradeResponse) throws IOException, ServletException {

		response.setStatus(upgradeResponse.getStatus());
		for (Map.Entry<String, List<String>> entry : upgradeResponse.getHeaders().entrySet()) {
			response.addHeader(entry.getKey(), Utils.getHeaderFromList(entry.getValue()));
		}

		AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(-1L);

		Object nativeRequest = getNativeRequest(request);
		BeanWrapper beanWrapper = new BeanWrapperImpl(nativeRequest);
		Object httpSocket = beanWrapper.getPropertyValue("connection.connectionHandler.rawConnection");
		Object webSocket = webSocketHelper.newInstance(request, httpSocket);
		webSocketHelper.upgrade(webSocket, httpSocket, request.getServletContext());

		response.flushBuffer();

		boolean isProtected = request.getUserPrincipal() != null;
		Writer servletWriter = servletWriterHelper.newInstance(webSocket, isProtected);
		Connection connection = upgradeInfo.createConnection(servletWriter, noOpCloseListener);
		new BeanWrapperImpl(webSocket).setPropertyValue("connection", connection);
		new BeanWrapperImpl(servletWriter).setPropertyValue("connection", connection);
		webSocketHelper.registerForReadEvent(webSocket);
	}
