	@Test
	public void testInheritanceWithSecondaryTable() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final TopLevelEntity top = new TopLevelEntity();

			final GeographicArea area1 = new GeographicArea();
			area1.setTopLevel( top );
			area1.setShape( new ShapePolygonEntity() );
			top.getGeographicAreas().add( area1 );

			final ShapeCircleEntity circle = new ShapeCircleEntity();
			circle.setCentre( "CENTRE" );

			final GeographicArea area2 = new GeographicArea();
			area2.setTopLevel( top );
			area2.setShape( circle );
			top.getGeographicAreas().add( area2 );

			entityManager.persist( top );
			entityManager.flush();
		} );
	}
