	@Test
	public void testPointcutEvents() throws Exception {
		this.reader.loadBeanDefinitions(POINTCUT_EVENTS_CONTEXT);
		ComponentDefinition[] componentDefinitions = this.eventListener.getComponentDefinitions();
		assertEquals("Incorrect number of events fired", 1, componentDefinitions.length);
		assertTrue("No holder with nested components", componentDefinitions[0] instanceof CompositeComponentDefinition);

		CompositeComponentDefinition compositeDef = (CompositeComponentDefinition) componentDefinitions[0];
		assertEquals("aop:config", compositeDef.getName());

		ComponentDefinition[] nestedComponentDefs = compositeDef.getNestedComponents();
		assertEquals("Incorrect number of inner components", 2, nestedComponentDefs.length);
		PointcutComponentDefinition pcd = null;
		for (int i = 0; i < nestedComponentDefs.length; i++) {
			ComponentDefinition componentDefinition = nestedComponentDefs[i];
			if (componentDefinition instanceof PointcutComponentDefinition) {
				pcd = (PointcutComponentDefinition) componentDefinition;
				break;
			}
		}
		assertNotNull("PointcutComponentDefinition not found", pcd);
		assertEquals("Incorrect number of BeanDefinitions", 1, pcd.getBeanDefinitions().length);
	}
