	@Test
	public void naming() {
		String className = GeneratedNameBean.class.getName();

		String targetName = className + BeanDefinitionReaderUtils.GENERATED_BEAN_NAME_SEPARATOR + "0";
		GeneratedNameBean topLevel1 = (GeneratedNameBean) beanFactory.getBean(targetName);
		assertNotNull(topLevel1);

		targetName = className + BeanDefinitionReaderUtils.GENERATED_BEAN_NAME_SEPARATOR + "1";
		GeneratedNameBean topLevel2 = (GeneratedNameBean) beanFactory.getBean(targetName);
		assertNotNull(topLevel2);

		GeneratedNameBean child1 = topLevel1.getChild();
		assertNotNull(child1.getBeanName());
		assertTrue(child1.getBeanName().startsWith(className));

		GeneratedNameBean child2 = topLevel2.getChild();
		assertNotNull(child2.getBeanName());
		assertTrue(child2.getBeanName().startsWith(className));

		assertFalse(child1.getBeanName().equals(child2.getBeanName()));
	}
