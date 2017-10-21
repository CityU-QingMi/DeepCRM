	@Test
	public void testWithThreeArgsShouldFail() {
		AbstractBean bean = (AbstractBean) beanFactory.getBean("abstractBean");
		assertNotNull(bean);
		try {
			bean.getThreeArguments("name", 1, 2);
			fail("TestBean does not have a three arg constructor so this should not have worked");
		}
		catch (AbstractMethodError ex) {
		}
		assertSame(bean, beanFactory.getBean(BeanConsumer.class).abstractBean);
	}
