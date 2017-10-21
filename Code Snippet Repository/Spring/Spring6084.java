	private static void printAST(PrintStream out, SpelNode t, String indent) {
		if (t != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(indent).append(t.getClass().getSimpleName());
			sb.append("  value:").append(t.toStringAST());
			sb.append(t.getChildCount() < 2 ? "" : "  #children:" + t.getChildCount());
			out.println(sb.toString());
			for (int i = 0; i < t.getChildCount(); i++) {
				printAST(out, t.getChild(i), indent + "  ");
			}
		}
	}
