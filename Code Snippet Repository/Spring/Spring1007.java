	@Test
	public void setMapPropertyWithTypeConversion() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(TestBean.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (!StringUtils.hasLength(text)) {
					throw new IllegalArgumentException();
				}
				setValue(new TestBean(text));
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("map[key1]", "rod");
		pvs.add("map[key2]", "rob");
		accessor.setPropertyValues(pvs);
		assertEquals("rod", ((TestBean) target.getMap().get("key1")).getName());
		assertEquals("rob", ((TestBean) target.getMap().get("key2")).getName());

		pvs = new MutablePropertyValues();
		pvs.add("map[key1]", "rod");
		pvs.add("map[key2]", "");
		try {
			accessor.setPropertyValues(pvs);
			fail("Should have thrown TypeMismatchException");
		}
		catch (PropertyBatchUpdateException ex) {
			PropertyAccessException pae = ex.getPropertyAccessException("map[key2]");
			assertTrue(pae instanceof TypeMismatchException);
		}
	}
