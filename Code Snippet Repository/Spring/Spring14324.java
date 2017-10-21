	@Test
	public void abstractApplicationContextValidatesRequiredPropertiesOnRefresh() {
		{
			ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.refresh();
		}

		{
			ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setRequiredProperties("foo", "bar");
			try {
				ctx.refresh();
				fail("expected missing property exception");
			}
			catch (MissingRequiredPropertiesException ex) {
			}
		}

		{
			ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setRequiredProperties("foo");
			ctx.setEnvironment(new MockEnvironment().withProperty("foo", "fooValue"));
			ctx.refresh(); // should succeed
		}
	}
