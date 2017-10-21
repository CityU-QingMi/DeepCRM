	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final UniqueGroup uniqueGroup = new UniqueGroup();
			final GroupMember groupMember = new GroupMember();
			uniqueGroup.addMember( groupMember );
			entityManager.persist( uniqueGroup );
			entityManager.persist( groupMember );
			uniqueGroupId = uniqueGroup.getId();
			groupMemberId = groupMember.getId();
		} );
		// Revision 2
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final GroupMember groupMember = entityManager.find( GroupMember.class, groupMemberId );
			final MultiGroup multiGroup = new MultiGroup();
			groupMember.addMultiGroup( multiGroup );
			entityManager.persist( multiGroup );
		} );
	}
