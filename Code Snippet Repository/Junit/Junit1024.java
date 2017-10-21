	@BeforeEach
	void initTree() {
		engineDescriptor = new EngineDescriptor(UniqueId.forEngine("testEngine"), "testEngine");
		GroupDescriptor group1 = new GroupDescriptor(UniqueId.root("group", "group1"));
		engineDescriptor.addChild(group1);
		GroupDescriptor group2 = new GroupDescriptor(UniqueId.root("group", "group2"));
		engineDescriptor.addChild(group2);
		GroupDescriptor group11 = new GroupDescriptor(UniqueId.root("group", "group1-1"));
		group1.addChild(group11);

		group1.addChild(new LeafDescriptor(UniqueId.root("leaf", "leaf1-1")));
		group1.addChild(new LeafDescriptor(UniqueId.root("leaf", "leaf1-2")));

		group2.addChild(new LeafDescriptor(UniqueId.root("leaf", "leaf2-1")));

		group11.addChild(new LeafDescriptor(UniqueId.root("leaf", "leaf11-1")));
	}
