	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[] {
				MapEntity.class, MapValue.class,

				ColorTypeConverter.class,

				CustomColorTypeConverter.class,
				ImplicitEnumMapKeyConverter.class,
				ExplicitEnumMapKeyConverter.class,
				ImplicitEnumMapKeyOverridedConverter.class
		};
	}
