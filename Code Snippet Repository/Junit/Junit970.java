	@Test
	void emptyEngines() {
		TreeNode root = new TreeNode("<root>");
		root.addChild(new TreeNode(createEngineId("e-0", "engine zero"), "none"));
		root.addChild(new TreeNode(createEngineId("e-1", "engine one")).setResult(successful()));
		root.addChild(new TreeNode(createEngineId("e-2", "engine two")).setResult(failed(null)));
		root.addChild(new TreeNode(createEngineId("e-3", "engine three")).setResult(aborted(null)));
		new TreePrinter(out, Theme.UNICODE, true).print(root);
		assertIterableEquals(
			Arrays.asList( //
				"╷", //
				"├─ engine zero ↷ none", //
				"├─ engine one ✔", //
				"├─ engine two ✘", //
				"└─ engine three ■"), //
			actual());
	}
