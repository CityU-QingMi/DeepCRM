	@Test
	public void testGenericListPropertyWithInvalidElementType() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition rbd = new RootBeanDefinition(GenericIntegerBean.class);

		List<Integer> input = new ArrayList<>();
		input.add(1);
		rbd.getPropertyValues().add("testBeanList", input);

		bf.registerBeanDefinition("genericBean", rbd);
		try {
			bf.getBean("genericBean");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMessage().contains("genericBean") && ex.getMessage().contains("testBeanList[0]"));
			assertTrue(ex.getMessage().contains(TestBean.class.getName()) && ex.getMessage().contains("Integer"));
		}
	}
