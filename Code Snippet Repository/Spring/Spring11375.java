	@Test
	public void handlerMappingOrder() throws Exception {

		HandlerMapping hm1 = mock(HandlerMapping.class, withSettings().extraInterfaces(Ordered.class));
		HandlerMapping hm2 = mock(HandlerMapping.class, withSettings().extraInterfaces(Ordered.class));
		when(((Ordered) hm1).getOrder()).thenReturn(1);
		when(((Ordered) hm2).getOrder()).thenReturn(2);
		when((hm1).getHandler(any())).thenReturn(Mono.just((Supplier<String>) () -> "1"));
		when((hm2).getHandler(any())).thenReturn(Mono.just((Supplier<String>) () -> "2"));

		StaticApplicationContext context = new StaticApplicationContext();
		context.registerBean("b2", HandlerMapping.class, () -> hm2);
		context.registerBean("b1", HandlerMapping.class, () -> hm1);
		context.registerBean(HandlerAdapter.class, SupplierHandlerAdapter::new);
		context.registerBean(HandlerResultHandler.class, StringHandlerResultHandler::new);
		context.refresh();

		DispatcherHandler dispatcherHandler = new DispatcherHandler(context);

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		dispatcherHandler.handle(exchange).block(Duration.ofSeconds(0));
		assertEquals("1", exchange.getResponse().getBodyAsString().block(Duration.ofSeconds(5)));
	}
