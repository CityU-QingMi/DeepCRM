	@Test
	public void setPropertyIntermediateListIsNullWithBadConversionService() {
		Foo target = new Foo();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setConversionService(new GenericConversionService() {
			@Override
			public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
				throw new ConversionFailedException(sourceType, targetType, source, null);
			}
		});
		accessor.setAutoGrowNestedPaths(true);
		accessor.setPropertyValue("listOfMaps[0]['luckyNumber']", "9");
		assertEquals("9", target.listOfMaps.get(0).get("luckyNumber"));
	}
