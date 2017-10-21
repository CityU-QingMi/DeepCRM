	@Test
	public void testIntegrationWithAnnotationConfigApplicationContext_metaProfile() {
		Class<?> beanClass = MetaProfileAnnotatedComponent.class;
		String beanName = MetaProfileAnnotatedComponent.BEAN_NAME;
		{
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setDefaultProfiles(TEST_DEFAULT_PROFILE_NAME);
			// no active profiles are set
			ctx.register(beanClass);
			ctx.refresh();
			assertThat(ctx.containsBean(beanName), is(true));
		}
		{
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setDefaultProfiles(TEST_DEFAULT_PROFILE_NAME);
			ctx.getEnvironment().setActiveProfiles("dev");
			ctx.register(beanClass);
			ctx.refresh();
			assertThat(ctx.containsBean(beanName), is(true));
		}
		{
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setDefaultProfiles(TEST_DEFAULT_PROFILE_NAME);
			ctx.getEnvironment().setActiveProfiles("other");
			ctx.register(beanClass);
			ctx.refresh();
			assertThat(ctx.containsBean(beanName), is(false));
		}
	}
