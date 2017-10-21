	private void print(TreeNode node, String indent, boolean continuous) {
		if (node.visible) {
			printVisible(node, indent, continuous);
		}
		if (node.children.isEmpty()) {
			return;
		}
		if (node.visible) {
			indent += continuous ? theme.vertical() : theme.blank();
		}
		Iterator<TreeNode> iterator = node.children.iterator();
		while (iterator.hasNext()) {
			print(iterator.next(), indent, iterator.hasNext());
		}
	}
