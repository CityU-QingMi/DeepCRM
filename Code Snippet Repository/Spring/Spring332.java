	@Test
	public void testAspectEvent() throws Exception {
		this.reader.loadBeanDefinitions(CONTEXT);
		ComponentDefinition[] componentDefinitions = this.eventListener.getComponentDefinitions();
		assertEquals("Incorrect number of events fired", 5, componentDefinitions.length);

		assertTrue("No holder with nested components", componentDefinitions[0] instanceof CompositeComponentDefinition);
		CompositeComponentDefinition compositeDef = (CompositeComponentDefinition) componentDefinitions[0];
		assertEquals("aop:config", compositeDef.getName());

		ComponentDefinition[] nestedComponentDefs = compositeDef.getNestedComponents();
		assertEquals("Incorrect number of inner components", 2, nestedComponentDefs.length);
		AspectComponentDefinition acd = null;
		for (int i = 0; i < nestedComponentDefs.length; i++) {
			ComponentDefinition componentDefinition = nestedComponentDefs[i];
			if (componentDefinition instanceof AspectComponentDefinition) {
				acd = (AspectComponentDefinition) componentDefinition;
				break;
			}
		}

		assertNotNull("AspectComponentDefinition not found", acd);
		BeanDefinition[] beanDefinitions = acd.getBeanDefinitions();
		assertEquals(5, beanDefinitions.length);
		BeanReference[] beanReferences = acd.getBeanReferences();
		assertEquals(6, beanReferences.length);

		Set<String> expectedReferences = new HashSet<>();
		expectedReferences.add("pc");
		expectedReferences.add("countingAdvice");
		for (int i = 0; i < beanReferences.length; i++) {
			BeanReference beanReference = beanReferences[i];
			expectedReferences.remove(beanReference.getBeanName());
		}
		assertEquals("Incorrect references found", 0, expectedReferences.size());

		for (int i = 1; i < componentDefinitions.length; i++) {
			assertTrue(componentDefinitions[i] instanceof BeanComponentDefinition);
		}

		ComponentDefinition[] nestedComponentDefs2 = acd.getNestedComponents();
		assertEquals("Inner PointcutComponentDefinition not found", 1, nestedComponentDefs2.length);
		assertTrue(nestedComponentDefs2[0] instanceof PointcutComponentDefinition);
		PointcutComponentDefinition pcd = (PointcutComponentDefinition) nestedComponentDefs2[0];
		assertEquals("Incorrect number of BeanDefinitions", 1, pcd.getBeanDefinitions().length);
	}
