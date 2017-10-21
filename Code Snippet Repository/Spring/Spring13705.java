	private String fetchMacro(String name) throws Exception {
		ClassPathResource resource = new ClassPathResource("test.ftl", getClass());
		assertTrue(resource.exists());
		String all = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
		all = all.replace("\r\n", "\n");
		String[] macros = StringUtils.delimitedListToStringArray(all, "\n\n");
		for (String macro : macros) {
			if (macro.startsWith(name)) {
				return macro.substring(macro.indexOf("\n")).trim();
			}
		}
		return null;
	}
