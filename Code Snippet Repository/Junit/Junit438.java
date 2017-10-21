	@Test
	void inheritsTagsFromParent() throws Exception {
		UniqueId rootUniqueId = UniqueId.root("segment", "template");
		UniqueId parentUniqueId = rootUniqueId.append("class", "myClass");
		AbstractTestDescriptor parent = containerTestDescriptorWithTags(parentUniqueId,
			singleton(TestTag.create("foo")));

		TestTemplateTestDescriptor testDescriptor = new TestTemplateTestDescriptor(
			parentUniqueId.append("tmp", "testTemplate()"), MyTestCase.class,
			MyTestCase.class.getDeclaredMethod("testTemplate"));
		parent.addChild(testDescriptor);

		assertThat(testDescriptor.getTags()).containsExactlyInAnyOrder(TestTag.create("foo"), TestTag.create("bar"),
			TestTag.create("baz"));
	}
