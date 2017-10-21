	@Override
	protected void startInternal() {
		if (this.tcpClient == null) {
			StompDecoder decoder = new StompDecoder();
			if (this.headerInitializer != null) {
				decoder.setHeaderInitializer(this.headerInitializer);
			}
			ReactorNettyCodec<byte[]> codec = new StompReactorNettyCodec(decoder);
			this.tcpClient = new ReactorNettyTcpClient<>(this.relayHost, this.relayPort, codec);
		}

		if (logger.isInfoEnabled()) {
			logger.info("Connecting \"system\" session to " + this.relayHost + ":" + this.relayPort);
		}

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECT);
		accessor.setAcceptVersion("1.1,1.2");
		accessor.setLogin(this.systemLogin);
		accessor.setPasscode(this.systemPasscode);
		accessor.setHeartbeat(this.systemHeartbeatSendInterval, this.systemHeartbeatReceiveInterval);
		String virtualHost = getVirtualHost();
		if (virtualHost != null) {
			accessor.setHost(virtualHost);
		}
		accessor.setSessionId(SYSTEM_SESSION_ID);
		if (logger.isDebugEnabled()) {
			logger.debug("Forwarding " + accessor.getShortLogMessage(EMPTY_PAYLOAD));
		}

		SystemStompConnectionHandler handler = new SystemStompConnectionHandler(accessor);
		this.connectionHandlers.put(handler.getSessionId(), handler);

		this.stats.incrementConnectCount();
		this.tcpClient.connect(handler, new FixedIntervalReconnectStrategy(5000));
	}
