	@Test
	public void escape() {
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		sb.append("'");
		sb.append("\\");
		sb.append("/");
		sb.append("\t");
		sb.append("\n");
		sb.append("\r");
		sb.append("\f");
		sb.append("\b");
		sb.append("\013");
		assertEquals("\\\"\\'\\\\\\/\\t\\n\\n\\f\\b\\v", JavaScriptUtils.javaScriptEscape(sb.toString()));
	}
