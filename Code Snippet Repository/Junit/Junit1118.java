		@Test
		void descriptionForJavaMethodAndClassSources() throws Exception {
			DemoHierarchicalTestEngine engine = new DemoHierarchicalTestEngine("dummy");
			Method failingTest = getClass().getDeclaredMethod("failingTest");
			DemoHierarchicalContainerDescriptor containerDescriptor = engine.addContainer("uniqueContainerName",
				"containerDisplayName", ClassSource.from(getClass()));
			containerDescriptor.addChild(
				new DemoHierarchicalTestDescriptor(containerDescriptor.getUniqueId().append("test", "failingTest"),
					"testDisplayName", MethodSource.from(failingTest), () -> {
					}));

			JUnitPlatform platformRunner = new JUnitPlatform(TestClass.class, createLauncher(engine));

			List<Description> children = platformRunner.getDescription().getChildren();
			assertEquals(1, children.size());
			Description engineDescription = children.get(0);
			assertEquals("dummy", engineDescription.getDisplayName());

			Description containerDescription = getOnlyElement(engineDescription.getChildren());
			Description testDescription = getOnlyElement(containerDescription.getChildren());

			// @formatter:off
			assertAll(
					() -> assertEquals("dummy", engineDescription.getDisplayName(), "engine display name"),
					() -> assertEquals("dummy", engineDescription.getClassName(), "engine class name"),
					() -> assertNull(engineDescription.getMethodName(), "engine method name"),
					() -> assertEquals("containerDisplayName", containerDescription.getDisplayName(), "container display name"),
					() -> assertEquals("containerDisplayName", containerDescription.getClassName(), "container class name"),
					() -> assertNull(containerDescription.getMethodName(), "container method name"),
					() -> assertEquals("testDisplayName(containerDisplayName)", testDescription.getDisplayName(), "test display name"),
					() -> assertEquals("containerDisplayName", testDescription.getClassName(), "test class name"),
					() -> assertEquals("testDisplayName", testDescription.getMethodName(), "test method name")
			);
			// @formatter:on
		}
