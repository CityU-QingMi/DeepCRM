	@Test
	public void pathVarsInModel() throws Exception {
		final Map<String, Object> pathVars = new HashMap<>();
		pathVars.put("hotel", "42");
		pathVars.put("booking", 21);
		pathVars.put("other", "other");

		WebApplicationContext wac =
			initServlet(new ApplicationContextInitializer<GenericWebApplicationContext>() {
				@Override
				public void initialize(GenericWebApplicationContext context) {
					RootBeanDefinition beanDef = new RootBeanDefinition(ModelValidatingViewResolver.class);
					beanDef.getConstructorArgumentValues().addGenericArgumentValue(pathVars);
					context.registerBeanDefinition("viewResolver", beanDef);
				}
			}, ViewRenderingController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels/42;q=1,2/bookings/21-other;q=3;r=R");
		getServlet().service(request, new MockHttpServletResponse());

		ModelValidatingViewResolver resolver = wac.getBean(ModelValidatingViewResolver.class);
		assertEquals(3, resolver.validatedAttrCount);
	}
