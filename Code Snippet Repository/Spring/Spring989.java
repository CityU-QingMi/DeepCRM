	@Test
	public void setStringArrayPropertyWithCustomStringEditor() throws Exception {
		PropsTester target = new PropsTester();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(String.class, "stringArray", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text.substring(1));
			}
		});

		accessor.setPropertyValue("stringArray", new String[] {"4foo", "7fi", "6fi", "5fum"});
		assertTrue("stringArray length = 4", target.stringArray.length == 4);
		assertTrue("correct values", target.stringArray[0].equals("foo") && target.stringArray[1].equals("fi") &&
				target.stringArray[2].equals("fi") && target.stringArray[3].equals("fum"));

		List<String> list = new ArrayList<>();
		list.add("4foo");
		list.add("7fi");
		list.add("6fi");
		list.add("5fum");
		accessor.setPropertyValue("stringArray", list);
		assertTrue("stringArray length = 4", target.stringArray.length == 4);
		assertTrue("correct values", target.stringArray[0].equals("foo") && target.stringArray[1].equals("fi") &&
				target.stringArray[2].equals("fi") && target.stringArray[3].equals("fum"));

		Set<String> set = new HashSet<>();
		set.add("4foo");
		set.add("7fi");
		set.add("6fum");
		accessor.setPropertyValue("stringArray", set);
		assertTrue("stringArray length = 3", target.stringArray.length == 3);
		List<String> result = Arrays.asList(target.stringArray);
		assertTrue("correct values", result.contains("foo") && result.contains("fi") && result.contains("fum"));

		accessor.setPropertyValue("stringArray", "8one");
		assertTrue("stringArray length = 1", target.stringArray.length == 1);
		assertTrue("correct values", target.stringArray[0].equals("one"));
	}
