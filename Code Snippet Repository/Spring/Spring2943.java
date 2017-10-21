	@Test
	public void autowiredConstructorArgumentWithSingleNonQualifiedCandidate() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person = new RootBeanDefinition(Person.class, cavs, null);
		context.registerBeanDefinition(JUERGEN, person);
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedConstructorArgumentTestBean.class));
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		try {
			context.refresh();
			fail("expected BeanCreationException");
		}
		catch (BeanCreationException e) {
			assertTrue(e instanceof UnsatisfiedDependencyException);
			assertEquals("autowired", e.getBeanName());
		}
	}
