	@Override
	public void initializeNativeSession(Session session) {
		super.initializeNativeSession(session);

		this.id = ObjectUtils.getIdentityHexString(getNativeSession());
		this.uri = session.getUpgradeRequest().getRequestURI();

		HttpHeaders headers = new HttpHeaders();
		headers.putAll(session.getUpgradeRequest().getHeaders());
		this.headers = HttpHeaders.readOnlyHttpHeaders(headers);

		this.acceptedProtocol = session.getUpgradeResponse().getAcceptedSubProtocol();

		List<ExtensionConfig> jettyExtensions = session.getUpgradeResponse().getExtensions();
		if (!CollectionUtils.isEmpty(jettyExtensions)) {
			List<WebSocketExtension> extensions = new ArrayList<>(jettyExtensions.size());
			for (ExtensionConfig jettyExtension : jettyExtensions) {
				extensions.add(new WebSocketExtension(jettyExtension.getName(), jettyExtension.getParameters()));
			}
			this.extensions = Collections.unmodifiableList(extensions);
		}
		else {
			this.extensions = Collections.emptyList();
		}

		if (this.user == null) {
			this.user = session.getUpgradeRequest().getUserPrincipal();
		}
	}
