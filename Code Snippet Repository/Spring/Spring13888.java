		public WebSocketHandlerContainer(
				JettyWebSocketHandlerAdapter handler, String protocol, List<WebSocketExtension> extensions) {

			this.handler = handler;
			this.selectedProtocol = protocol;
			if (CollectionUtils.isEmpty(extensions)) {
				this.extensionConfigs = new ArrayList<>(0);
			}
			else {
				this.extensionConfigs = new ArrayList<>(extensions.size());
				for (WebSocketExtension extension : extensions) {
					this.extensionConfigs.add(new WebSocketToJettyExtensionConfigAdapter(extension));
				}
			}
		}
