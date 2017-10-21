	@Override
	public void upgradeInternal(ServerHttpRequest request, ServerHttpResponse response,
			@Nullable String selectedProtocol, List<Extension> extensions, Endpoint endpoint)
			throws HandshakeFailureException {

		HttpServletRequest servletRequest = getHttpServletRequest(request);
		HttpServletResponse servletResponse = getHttpServletResponse(response);

		TyrusServerContainer serverContainer = (TyrusServerContainer) getContainer(servletRequest);
		TyrusWebSocketEngine engine = (TyrusWebSocketEngine) serverContainer.getWebSocketEngine();
		Object tyrusEndpoint = null;
		boolean success;

		try {
			// Shouldn't matter for processing but must be unique
			String path = "/" + random.nextLong();
			tyrusEndpoint = createTyrusEndpoint(endpoint, path, selectedProtocol, extensions, serverContainer, engine);
			register(engine, tyrusEndpoint);

			HttpHeaders headers = request.getHeaders();
			RequestContext requestContext = createRequestContext(servletRequest, path, headers);
			TyrusUpgradeResponse upgradeResponse = new TyrusUpgradeResponse();
			UpgradeInfo upgradeInfo = engine.upgrade(requestContext, upgradeResponse);
			success = SUCCESS.equals(upgradeInfo.getStatus());
			if (success) {
				if (logger.isTraceEnabled()) {
					logger.trace("Successful request upgrade: " + upgradeResponse.getHeaders());
				}
				handleSuccess(servletRequest, servletResponse, upgradeInfo, upgradeResponse);
			}
		}
		catch (Exception ex) {
			unregisterTyrusEndpoint(engine, tyrusEndpoint);
			throw new HandshakeFailureException("Error during handshake: " + request.getURI(), ex);
		}

		unregisterTyrusEndpoint(engine, tyrusEndpoint);
		if (!success) {
			throw new HandshakeFailureException("Unexpected handshake failure: " + request.getURI());
		}
	}
