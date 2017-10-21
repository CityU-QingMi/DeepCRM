	@Test
	public void testAuditQueryWithJoinedInheritanceUnrelatedPropertyJoin() {
		// The problem is that this query succeeds on DefaultAuditStrategy, fails on ValidityAuditStrategy
		//
		// ValidityAuditStrategy
		// ---------------------
		// select
		// 		joinedinhe0_.id as id1_1_,
		// 		joinedinhe0_.REV as REV2_1_,
		// 		joinedinhe0_.REVTYPE as REVTYPE3_1_,
		// 		joinedinhe0_.REVEND as REVEND4_1_,
		// 		joinedinhe0_.relationToC_id as relation5_1_
		// from
		// 		EntityA_AUD joinedinhe0_
		// 	inner join EntityC_AUD joinedinhe1_
		// 		on (joinedinhe0_.relationToC_id=joinedinhe1_.id or (joinedinhe0_.relationToC_id is null)
		// 			and (joinedinhe1_.id is null))
		// where
		// 	joinedinhe0_.REV<=?
		// and
		// 	joinedinhe0_.REVTYPE<>?
		// and
		// 	(joinedinhe0_.REVEND>? or joinedinhe0_.REVEND is null)
		// and
		// 	joinedinhe1_.REV<=?
		// and
		// 	(joinedinhe1_1_.REVEND>? or joinedinhe1_1_.REVEND is null)
		//
		// Error: SQL Error: 42122, SQLState: 42S22
		// Column "JOINEDINHE1_1_.REVEND" not found
		//
		List results = getAuditReader().createQuery().forEntitiesAtRevision( EntityA.class, 1 )
				.traverseRelation( "relationToC", JoinType.INNER )
				.getResultList();
		assertEquals( 1, results.size() );
	}
