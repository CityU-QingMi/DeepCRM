	@Test
	public void autowiredFieldDoesNotResolveWithMultipleQualifierValuesAndConflictingDefaultValue() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs1 = new ConstructorArgumentValues();
		cavs1.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person1 = new RootBeanDefinition(Person.class, cavs1, null);
		AutowireCandidateQualifier qualifier = new AutowireCandidateQualifier(TestQualifierWithMultipleAttributes.class);
		qualifier.setAttribute("number", 456);
		person1.addQualifier(qualifier);
		ConstructorArgumentValues cavs2 = new ConstructorArgumentValues();
		cavs2.addGenericArgumentValue(MARK);
		RootBeanDefinition person2 = new RootBeanDefinition(Person.class, cavs2, null);
		AutowireCandidateQualifier qualifier2 = new AutowireCandidateQualifier(TestQualifierWithMultipleAttributes.class);
		qualifier2.setAttribute("number", 123);
		qualifier2.setAttribute("value", "not the default");
		person2.addQualifier(qualifier2);
		context.registerBeanDefinition(JUERGEN, person1);
		context.registerBeanDefinition(MARK, person2);
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedFieldWithMultipleAttributesTestBean.class));
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
