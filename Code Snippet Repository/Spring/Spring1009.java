	@Test
	public void setMapPropertyWithCustomUnmodifiableMap() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(TestBean.class, "map", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (!StringUtils.hasLength(text)) {
					throw new IllegalArgumentException();
				}
				setValue(new TestBean(text));
			}
		});

		Map<Object, Object> inputMap = new HashMap<>();
		inputMap.put(1, "rod");
		inputMap.put(2, "rob");
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("map", new ReadOnlyMap<>(inputMap));
		accessor.setPropertyValues(pvs);
		assertEquals("rod", ((TestBean) target.getMap().get(1)).getName());
		assertEquals("rob", ((TestBean) target.getMap().get(2)).getName());
	}
