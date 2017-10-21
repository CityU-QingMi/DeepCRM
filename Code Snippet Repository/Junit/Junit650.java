	private void printVisible(TreeNode node, String indent, boolean continuous) {
		String bullet = continuous ? theme.entry() : theme.end();
		String prefix = color(CONTAINER, indent + bullet);
		String tabbed = color(CONTAINER, indent + (continuous ? theme.vertical() : theme.blank()) + theme.blank());
		String caption = colorCaption(node);
		String duration = color(CONTAINER, node.duration + " ms");
		String icon = color(SKIPPED, theme.skipped());
		if (node.result().isPresent()) {
			TestExecutionResult result = node.result().get();
			Color resultColor = Color.valueOf(result);
			icon = color(resultColor, theme.status(result));
		}
		out.print(prefix);
		out.print(" ");
		out.print(caption);
		if (node.duration > 10000 && node.children.isEmpty()) {
			out.print(" ");
			out.print(duration);
		}
		out.print(" ");
		out.print(icon);
		node.result().ifPresent(result -> printThrowable(tabbed, result));
		node.reason().ifPresent(reason -> printMessage(SKIPPED, tabbed, reason));
		node.reports.forEach(e -> printReportEntry(tabbed, e));
		out.println();
	}
