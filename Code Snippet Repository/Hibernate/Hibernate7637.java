	@Test
	@TestForIssue(jiraKey = "")
	public void testPaginationWithFormulaSubquery() {
		doInHibernate( this::sessionFactory, session -> {
			// populating test data
			Folder folder1 = new Folder( 1L, "Folder1" );
			Folder folder2 = new Folder( 2L, "Folder2" );
			Folder folder3 = new Folder( 3L, "Folder3" );
			session.persist( folder1 );
			session.persist( folder2 );
			session.persist( folder3 );
			session.flush();
			session.persist( new Contact( 1L, "Lukasz", "Antoniak", "owner", folder1 ) );
			session.persist( new Contact( 2L, "Kinga", "Mroz", "co-owner", folder2 ) );
			session.flush();
			session.clear();
			session.refresh( folder1 );
			session.refresh( folder2 );
			session.clear();

			List<Long> folderCount = session.createQuery( "select count(distinct f) from Folder f" ).setMaxResults( 1 ).list();
			assertEquals( Arrays.asList( 3L ), folderCount );

			List<Folder> distinctFolders = session.createQuery( "select distinct f from Folder f order by f.id desc" )
					.setFirstResult( 1 ).setMaxResults( 2 ).list();
			assertEquals( Arrays.asList( folder2, folder1 ), distinctFolders );
		} );
	}
