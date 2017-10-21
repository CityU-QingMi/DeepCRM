	@Test
	public void simpleBootstrap() {
		MockServletContext servletContext = new MockServletContext();

		TilesConfigurer tc = new TilesConfigurer();
		tc.setDefinitions("/org/springframework/web/servlet/view/tiles3/tiles-definitions.xml");
		tc.setCheckRefresh(true);
		tc.setServletContext(servletContext);
		tc.afterPropertiesSet();

		ApplicationContext tilesContext = ServletUtil.getApplicationContext(servletContext);

		BasicTilesContainer container = (BasicTilesContainer) TilesAccess.getContainer(tilesContext);
		Request requestContext = new ServletRequest(container.getApplicationContext(),
				new MockHttpServletRequest(), new MockHttpServletResponse());
		assertNotNull(container.getDefinitionsFactory().getDefinition("test", requestContext));

		tc.destroy();
	}
