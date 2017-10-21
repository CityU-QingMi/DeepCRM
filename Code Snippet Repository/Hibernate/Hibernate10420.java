	@Test
	@Priority(10)
	public void initData() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager-> {
			final Item item = new Item( "The Item" );
			entityManager.persist( item );

			final Category category = new Category( "The Category" );
			category.setDescription( "The description" );
			category.setValue( item, new Value( "The Value", 4711L ) );
			category.setText( item, "The text" );
			entityManager.persist( category );

			this.category = category;
			this.item = item;
		} );
	}
