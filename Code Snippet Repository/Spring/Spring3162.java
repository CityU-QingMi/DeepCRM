	@Test
	public void providesBeanMethodBeanDefinition() throws Exception {
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Conf.class);
		BeanDefinition beanDefinition = context.getBeanDefinition("myBean");
		assertThat("should provide AnnotatedBeanDefinition", beanDefinition, instanceOf(AnnotatedBeanDefinition.class));
		Map<String, Object> annotationAttributes =
				((AnnotatedBeanDefinition) beanDefinition).getFactoryMethodMetadata().getAnnotationAttributes(MyAnnotation.class.getName());
		assertThat(annotationAttributes.get("value"), equalTo("test"));
		context.close();
	}
