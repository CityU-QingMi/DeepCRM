	@Test
	public void testAllRevEndTimeStamps() {
		List<Map<String, Object>> p1RevList = getRevisions(
				ParentEntity.class,
				p1_id
		);
		List<Map<String, Object>> p2RevList = getRevisions(
				ParentEntity.class,
				p2_id
		);
		List<Map<String, Object>> c1_1_List = getRevisions(
				Child1Entity.class,
				c1_1_id
		);
		List<Map<String, Object>> c1_2_List = getRevisions(
				Child1Entity.class,
				c1_2_id
		);
		List<Map<String, Object>> c2_1_List = getRevisions(
				Child2Entity.class,
				c2_1_id
		);
		List<Map<String, Object>> c2_2_List = getRevisions(
				Child2Entity.class,
				c2_2_id
		);

		verifyRevEndTimeStamps( "ParentEntity: " + p1_id, p1RevList );
		verifyRevEndTimeStamps( "ParentEntity: " + p2_id, p2RevList );
		verifyRevEndTimeStamps( "Child1Entity: " + c1_1_id, c1_1_List );
		verifyRevEndTimeStamps( "Child1Entity: " + c1_2_id, c1_2_List );
		verifyRevEndTimeStamps( "Child2Entity: " + c2_1_id, c2_1_List );
		verifyRevEndTimeStamps( "Child2Entity: " + c2_2_id, c2_2_List );

	}
