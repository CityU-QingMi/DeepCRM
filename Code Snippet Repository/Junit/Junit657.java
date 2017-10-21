	private void printDetail(Color color, String detail, String format, Object... args) {
		// print initial verticals - expecting to be at start of the line
		String verticals = verticals(frames.size() + 1);
		printf(NONE, verticals);
		String detailFormat = "%9s";
		// omit detail string if it's empty
		if (!detail.isEmpty()) {
			printf(NONE, String.format(detailFormat + ": ", detail));
		}
		// trivial case: at least one arg is given? Let printf do the entire work
		if (args.length > 0) {
			printf(color, format, args);
			return;
		}
		// still here? Split format into separate lines and indent them from the second line on
		String[] lines = format.split("\\R");
		printf(color, lines[0]);
		if (lines.length > 1) {
			String delimiter = System.lineSeparator() + verticals + String.format(detailFormat + "    ", "");
			for (int i = 1; i < lines.length; i++) {
				printf(NONE, delimiter);
				printf(color, lines[i]);
			}
		}
		printf(NONE, "%n");
	}
