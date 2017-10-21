	@Test
	public void componentScanRespectsProfileAnnotation() {
		String xmlLocation = "org/springframework/context/annotation/componentScanRespectsProfileAnnotationTests.xml";
		{ // should exclude the profile-annotated bean if active profiles remains unset
			GenericXmlApplicationContext context = new GenericXmlApplicationContext();
			context.load(xmlLocation);
			context.refresh();
			assertThat(context.containsBean(ProfileAnnotatedComponent.BEAN_NAME), is(false));
			context.close();
		}
		{ // should include the profile-annotated bean with active profiles set
			GenericXmlApplicationContext context = new GenericXmlApplicationContext();
			context.getEnvironment().setActiveProfiles(ProfileAnnotatedComponent.PROFILE_NAME);
			context.load(xmlLocation);
			context.refresh();
			assertThat(context.containsBean(ProfileAnnotatedComponent.BEAN_NAME), is(true));
			context.close();
		}
		{ // ensure the same works for AbstractRefreshableApplicationContext impls too
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] { xmlLocation },
				false);
			context.getEnvironment().setActiveProfiles(ProfileAnnotatedComponent.PROFILE_NAME);
			context.refresh();
			assertThat(context.containsBean(ProfileAnnotatedComponent.BEAN_NAME), is(true));
			context.close();
		}
	}
