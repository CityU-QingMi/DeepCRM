	private void printMessage(Color color, String indent, String message) {
		String[] lines = message.split("\\R");
		out.print(" ");
		out.print(color(color, lines[0]));
		if (lines.length > 1) {
			for (int i = 1; i < lines.length; i++) {
				out.println();
				out.print(indent);
				if (StringUtils.isNotBlank(lines[i])) {
					String extra = theme.blank();
					out.print(color(color, extra + lines[i]));
				}
			}
		}
	}
