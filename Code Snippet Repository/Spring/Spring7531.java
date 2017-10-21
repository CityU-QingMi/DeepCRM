	@Test
	public void registerAndUnregisterMultipleDestinations() {
		String sess1 = "sess01";
		String sess2 = "sess02";

		String subs1 = "subs01";
		String subs2 = "subs02";
		String subs3 = "subs03";
		String subs4 = "subs04";
		String subs5 = "subs05";

		this.registry.registerSubscription(subscribeMessage(sess1, subs1, "/topic/PRICE.STOCK.NASDAQ.IBM"));
		this.registry.registerSubscription(subscribeMessage(sess1, subs2, "/topic/PRICE.STOCK.NYSE.IBM"));
		this.registry.registerSubscription(subscribeMessage(sess1, subs3, "/topic/PRICE.STOCK.NASDAQ.GOOG"));

		this.registry.findSubscriptions(createMessage("/topic/PRICE.STOCK.NYSE.IBM"));
		this.registry.findSubscriptions(createMessage("/topic/PRICE.STOCK.NASDAQ.GOOG"));
		this.registry.findSubscriptions(createMessage("/topic/PRICE.STOCK.NASDAQ.IBM"));

		this.registry.unregisterSubscription(unsubscribeMessage(sess1, subs1));
		this.registry.unregisterSubscription(unsubscribeMessage(sess1, subs2));
		this.registry.unregisterSubscription(unsubscribeMessage(sess1, subs3));

		this.registry.registerSubscription(subscribeMessage(sess1, subs1, "/topic/PRICE.STOCK.NASDAQ.IBM"));
		this.registry.registerSubscription(subscribeMessage(sess1, subs2, "/topic/PRICE.STOCK.NYSE.IBM"));
		this.registry.registerSubscription(subscribeMessage(sess1, subs3, "/topic/PRICE.STOCK.NASDAQ.GOOG"));
		this.registry.registerSubscription(subscribeMessage(sess1, subs4, "/topic/PRICE.STOCK.NYSE.IBM"));
		this.registry.registerSubscription(subscribeMessage(sess2, subs5, "/topic/PRICE.STOCK.NASDAQ.GOOG"));

		this.registry.unregisterAllSubscriptions(sess1);
		this.registry.unregisterAllSubscriptions(sess2);
	}
