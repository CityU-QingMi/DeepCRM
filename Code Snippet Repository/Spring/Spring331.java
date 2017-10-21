	@Test
	public void testAdvisorEventsWithDirectPointcut() throws Exception {
		this.reader.loadBeanDefinitions(DIRECT_POINTCUT_EVENTS_CONTEXT);
		ComponentDefinition[] componentDefinitions = this.eventListener.getComponentDefinitions();
		assertEquals("Incorrect number of events fired", 2, componentDefinitions.length);

		assertTrue("No holder with nested components", componentDefinitions[0] instanceof CompositeComponentDefinition);
		CompositeComponentDefinition compositeDef = (CompositeComponentDefinition) componentDefinitions[0];
		assertEquals("aop:config", compositeDef.getName());

		ComponentDefinition[] nestedComponentDefs = compositeDef.getNestedComponents();
		assertEquals("Incorrect number of inner components", 2, nestedComponentDefs.length);
		AdvisorComponentDefinition acd = null;
		for (int i = 0; i < nestedComponentDefs.length; i++) {
			ComponentDefinition componentDefinition = nestedComponentDefs[i];
			if (componentDefinition instanceof AdvisorComponentDefinition) {
				acd = (AdvisorComponentDefinition) componentDefinition;
				break;
			}
		}
		assertNotNull("AdvisorComponentDefinition not found", acd);
		assertEquals(2, acd.getBeanDefinitions().length);
		assertEquals(1, acd.getBeanReferences().length);

		assertTrue("No advice bean found", componentDefinitions[1] instanceof BeanComponentDefinition);
		BeanComponentDefinition adviceDef = (BeanComponentDefinition) componentDefinitions[1];
		assertEquals("countingAdvice", adviceDef.getBeanName());
	}
