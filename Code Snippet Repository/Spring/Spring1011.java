	@SuppressWarnings("")
	@Test
	public void setRawMapPropertyWithNoEditorRegistered() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		Map inputMap = new HashMap();
		inputMap.put(1, "rod");
		inputMap.put(2, "rob");
		ReadOnlyMap readOnlyMap = new ReadOnlyMap(inputMap);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("map", readOnlyMap);
		accessor.setPropertyValues(pvs);
		assertSame(readOnlyMap, target.getMap());
		assertFalse(readOnlyMap.isAccessed());
	}
