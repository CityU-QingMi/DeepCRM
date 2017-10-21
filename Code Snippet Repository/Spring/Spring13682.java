	@Test
	public void testXmlViewResolverWithoutCache() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext() {
			@Override
			protected Resource getResourceByPath(String path) {
				assertTrue("Correct default location", XmlViewResolver.DEFAULT_LOCATION.equals(path));
				return super.getResourceByPath(path);
			}
		};
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		XmlViewResolver vr = new XmlViewResolver();
		vr.setCache(false);
		try {
			vr.setApplicationContext(wac);
		}
		catch (ApplicationContextException ex) {
			fail("Should not have thrown ApplicationContextException: " + ex.getMessage());
		}
		try {
			vr.resolveViewName("example1", Locale.getDefault());
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			// expected
		}
	}
