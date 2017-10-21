	@Test
	public void setIntArrayPropertyWithCustomEditor() {
		PropsTester target = new PropsTester();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(int.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(new Integer(Integer.parseInt(text) + 1));
			}
		});

		accessor.setPropertyValue("intArray", new int[] {4, 5, 2, 3});
		assertTrue("intArray length = 4", target.intArray.length == 4);
		assertTrue("correct values", target.intArray[0] == 4 && target.intArray[1] == 5 &&
				target.intArray[2] == 2 && target.intArray[3] == 3);

		accessor.setPropertyValue("intArray", new String[] {"3", "4", "1", "2"});
		assertTrue("intArray length = 4", target.intArray.length == 4);
		assertTrue("correct values", target.intArray[0] == 4 && target.intArray[1] == 5 &&
				target.intArray[2] == 2 && target.intArray[3] == 3);

		accessor.setPropertyValue("intArray", new Integer(1));
		assertTrue("intArray length = 4", target.intArray.length == 1);
		assertTrue("correct values", target.intArray[0] == 1);

		accessor.setPropertyValue("intArray", new String[] {"0"});
		assertTrue("intArray length = 4", target.intArray.length == 1);
		assertTrue("correct values", target.intArray[0] == 1);

		accessor.setPropertyValue("intArray", "0");
		assertTrue("intArray length = 4", target.intArray.length == 1);
		assertTrue("correct values", target.intArray[0] == 1);
	}
