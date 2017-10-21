	@Test
	public void setStringArrayProperty() throws Exception {
		PropsTester target = new PropsTester();
		AbstractPropertyAccessor accessor = createAccessor(target);

		accessor.setPropertyValue("stringArray", new String[] {"foo", "fi", "fi", "fum"});
		assertTrue("stringArray length = 4", target.stringArray.length == 4);
		assertTrue("correct values", target.stringArray[0].equals("foo") && target.stringArray[1].equals("fi") &&
				target.stringArray[2].equals("fi") && target.stringArray[3].equals("fum"));

		List<String> list = new ArrayList<>();
		list.add("foo");
		list.add("fi");
		list.add("fi");
		list.add("fum");
		accessor.setPropertyValue("stringArray", list);
		assertTrue("stringArray length = 4", target.stringArray.length == 4);
		assertTrue("correct values", target.stringArray[0].equals("foo") && target.stringArray[1].equals("fi") &&
				target.stringArray[2].equals("fi") && target.stringArray[3].equals("fum"));

		Set<String> set = new HashSet<>();
		set.add("foo");
		set.add("fi");
		set.add("fum");
		accessor.setPropertyValue("stringArray", set);
		assertTrue("stringArray length = 3", target.stringArray.length == 3);
		List<String> result = Arrays.asList(target.stringArray);
		assertTrue("correct values", result.contains("foo") && result.contains("fi") && result.contains("fum"));

		accessor.setPropertyValue("stringArray", "one");
		assertTrue("stringArray length = 1", target.stringArray.length == 1);
		assertTrue("stringArray elt is ok", target.stringArray[0].equals("one"));

		accessor.setPropertyValue("stringArray", null);
		assertTrue("stringArray is null", target.stringArray == null);
	}
