	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		StrIntTestEntity nullSite = new StrIntTestEntity( null, 1 );
		StrIntTestEntity notNullSite = new StrIntTestEntity( "data", 2 );
		em.persist( nullSite );
		em.persist( notNullSite );
		idSimplePropertyNull = nullSite.getId();
		idSimplePropertyNotNull = notNullSite.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		SetRefIngEmbIdEntity nullParentSrieie = new SetRefIngEmbIdEntity(
				idMulticolumnReferenceToParentNull,
				"data",
				null
		);
		em.persist( nullParentSrieie );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		CollectionRefEdEntity parent = new CollectionRefEdEntity( idParent, "data" );
		CollectionRefIngEntity notNullParentCrie = new CollectionRefIngEntity(
				idReferenceToParentNotNull,
				"data",
				parent
		);
		em.persist( parent );
		em.persist( notNullParentCrie );
		em.getTransaction().commit();
	}
