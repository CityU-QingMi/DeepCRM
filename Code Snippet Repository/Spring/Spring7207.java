	@Override
	protected List<? extends HandlerMethodReturnValueHandler> initReturnValueHandlers() {
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();

		// Single-purpose return value types
		handlers.add(new ListenableFutureReturnValueHandler());
		handlers.add(new CompletableFutureReturnValueHandler());

		// Annotation-based return value types
		SendToMethodReturnValueHandler sendToHandler =
				new SendToMethodReturnValueHandler(this.brokerTemplate, true);
		if (this.headerInitializer != null) {
			sendToHandler.setHeaderInitializer(this.headerInitializer);
		}
		handlers.add(sendToHandler);

		SubscriptionMethodReturnValueHandler subscriptionHandler =
				new SubscriptionMethodReturnValueHandler(this.clientMessagingTemplate);
		subscriptionHandler.setHeaderInitializer(this.headerInitializer);
		handlers.add(subscriptionHandler);

		// custom return value types
		handlers.addAll(getCustomReturnValueHandlers());

		// catch-all
		sendToHandler = new SendToMethodReturnValueHandler(this.brokerTemplate, false);
		sendToHandler.setHeaderInitializer(this.headerInitializer);
		handlers.add(sendToHandler);

		return handlers;
	}
