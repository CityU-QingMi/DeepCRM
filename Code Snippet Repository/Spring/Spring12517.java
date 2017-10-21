	@Test
	public void testSingletonDestructionOnStartupFailure() throws IOException {
		try {
			new ClassPathXmlApplicationContext(new String[] {
				"/org/springframework/web/context/WEB-INF/applicationContext.xml",
				"/org/springframework/web/context/WEB-INF/fail.xml" }) {

				@Override
				public void refresh() throws BeansException {
					try {
						super.refresh();
					}
					catch (BeanCreationException ex) {
						DefaultListableBeanFactory factory = (DefaultListableBeanFactory) getBeanFactory();
						assertEquals(0, factory.getSingletonCount());
						throw ex;
					}
				}
			};
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
		}
	}
