	@Test
	public void orderedInterceptors() throws Exception {
		MappedInterceptor firstMappedInterceptor = new MappedInterceptor(new String[]{"/**"}, Mockito.mock(HandlerInterceptor.class));
		HandlerInterceptor secondHandlerInterceptor = Mockito.mock(HandlerInterceptor.class);
		MappedInterceptor thirdMappedInterceptor = new MappedInterceptor(new String[]{"/**"}, Mockito.mock(HandlerInterceptor.class));
		HandlerInterceptor fourthHandlerInterceptor = Mockito.mock(HandlerInterceptor.class);

		this.handlerMapping.setInterceptors(new Object[]{firstMappedInterceptor, secondHandlerInterceptor,
				thirdMappedInterceptor, fourthHandlerInterceptor});
		this.handlerMapping.setApplicationContext(this.context);
		HandlerExecutionChain chain = this.handlerMapping.getHandlerExecutionChain(new SimpleHandler(), this.request);
		Assert.assertThat(chain.getInterceptors(),
				Matchers.arrayContaining(firstMappedInterceptor.getInterceptor(), secondHandlerInterceptor,
						thirdMappedInterceptor.getInterceptor(), fourthHandlerInterceptor));
	}
