	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class<?>[]{
				ShapeEntity.class,
				ShapePolygonEntity.class,
				ShapeCircleEntity.class,
				GeographicArea.class,
				TopLevelEntity.class
		};
	}
