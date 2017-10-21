	@Test
	public void getDetailedLogMessageWithValuesSet() {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
		accessor.setDestination("/destination");
		accessor.setSubscriptionId("subscription");
		accessor.setSessionId("session");
		accessor.setUser(new TestPrincipal("user"));
		accessor.setSessionAttributes(Collections.<String, Object>singletonMap("key", "value"));
		accessor.setNativeHeader("nativeKey", "nativeValue");

		assertEquals("MESSAGE destination=/destination subscriptionId=subscription " +
				"session=session user=user attributes={key=value} nativeHeaders=" +
				"{nativeKey=[nativeValue]} payload=p", accessor.getDetailedLogMessage("p"));
	}
