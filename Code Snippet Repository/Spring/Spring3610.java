	@Test
	public void shouldSupportJodaStylePatterns() throws Exception {
		String[] chars = { "S", "M", "-" };
		for (String d : chars) {
			for (String t : chars) {
				String style = d + t;
				if (!style.equals("--")) {
					Date date = getDate(2009, Calendar.JUNE, 10, 14, 23, 0, 0);
					if (t.equals("-")) {
						date = getDate(2009, Calendar.JUNE, 10);
					}
					else if (d.equals("-")) {
						date = getDate(1970, Calendar.JANUARY, 1, 14, 23, 0, 0);
					}
					testJodaStylePatterns(style, Locale.US, date);
				}
			}
		}
	}
