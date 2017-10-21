	@Override
	protected void handleSuccess(HttpServletRequest request, HttpServletResponse response,
			UpgradeInfo upgradeInfo, TyrusUpgradeResponse upgradeResponse) throws IOException, ServletException {

		TyrusHttpUpgradeHandler handler = request.upgrade(TyrusHttpUpgradeHandler.class);
		Writer servletWriter = newServletWriter(handler);
		handler.preInit(upgradeInfo, servletWriter, request.getUserPrincipal() != null);

		response.setStatus(upgradeResponse.getStatus());
		for (Map.Entry<String, List<String>> entry : upgradeResponse.getHeaders().entrySet()) {
			response.addHeader(entry.getKey(), Utils.getHeaderFromList(entry.getValue()));
		}
		response.flushBuffer();
	}
