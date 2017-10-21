	private void assertFactoryCountThroughoutLifecycle(ConfigurableApplicationContext ctx) throws Exception {
		assertThat(serializableFactoryCount(), equalTo(0));
		try {
			ctx.refresh();
			assertThat(serializableFactoryCount(), equalTo(1));
			ctx.close();
		}
		catch (BeanCreationException ex) {
			// ignore - this is expected on refresh() for failure case tests
		}
		finally {
			assertThat(serializableFactoryCount(), equalTo(0));
		}
	}
