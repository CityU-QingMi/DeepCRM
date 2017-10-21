	@Test
	public void testCreatedAuditTables() {
		Table explicitTransChildTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditparents.ExplicitTransitiveChildEntity_AUD"
		).getTable();
		checkTableColumns(
				TestTools.makeSet( "child", "parent", "grandparent", "id" ),
				TestTools.makeSet( "notAudited" ),
				explicitTransChildTable
		);

		Table implicitTransChildTable = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.superclass.auditparents.ImplicitTransitiveChildEntity_AUD"
		).getTable();
		checkTableColumns(
				TestTools.makeSet( "child", "parent", "grandparent", "id" ),
				TestTools.makeSet( "notAudited" ),
				implicitTransChildTable
		);
	}
