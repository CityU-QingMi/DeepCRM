	@Test
	public void testAutowiredMethodParameterWithMultipleNonQualifiedCandidates() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs1 = new ConstructorArgumentValues();
		cavs1.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person1 = new RootBeanDefinition(Person.class, cavs1, null);
		ConstructorArgumentValues cavs2 = new ConstructorArgumentValues();
		cavs2.addGenericArgumentValue(MARK);
		RootBeanDefinition person2 = new RootBeanDefinition(Person.class, cavs2, null);
		context.registerBeanDefinition(JUERGEN, person1);
		context.registerBeanDefinition(MARK, person2);
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedMethodParameterTestBean.class));
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		try {
			context.refresh();
			fail("expected BeanCreationException");
		}
		catch (BeanCreationException e) {
			assertTrue(e.getRootCause() instanceof NoSuchBeanDefinitionException);
			assertEquals("autowired", e.getBeanName());
		}
	}
