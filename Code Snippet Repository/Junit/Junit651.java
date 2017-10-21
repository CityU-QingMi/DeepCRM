	private String colorCaption(TreeNode node) {
		String caption = node.caption();
		if (node.result().isPresent()) {
			TestExecutionResult result = node.result().get();
			Color resultColor = Color.valueOf(result);
			if (result.getStatus() != Status.SUCCESSFUL) {
				return color(resultColor, caption);
			}
		}
		if (node.reason().isPresent()) {
			return color(SKIPPED, caption);
		}
		return color(Color.valueOf(node.identifier().orElseThrow(AssertionError::new)), caption);
	}
