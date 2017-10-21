	@Test
	public void testCacheUnresolved() throws Exception {
		final AtomicInteger count = new AtomicInteger();
		AbstractCachingViewResolver viewResolver = new AbstractCachingViewResolver() {
			@Override
			protected View loadView(String viewName, Locale locale) throws Exception {
				count.incrementAndGet();
				return null;
			}
		};

		viewResolver.setCacheUnresolved(false);

		viewResolver.resolveViewName("view", Locale.getDefault());
		viewResolver.resolveViewName("view", Locale.getDefault());

		assertEquals(2, count.intValue());

		viewResolver.setCacheUnresolved(true);

		viewResolver.resolveViewName("view", Locale.getDefault());
		viewResolver.resolveViewName("view", Locale.getDefault());
		viewResolver.resolveViewName("view", Locale.getDefault());
		viewResolver.resolveViewName("view", Locale.getDefault());
		viewResolver.resolveViewName("view", Locale.getDefault());

		assertEquals(3, count.intValue());
	}
