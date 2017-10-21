	@SuppressWarnings( {""})
	private void cleanup() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		for ( Hoarder hoarder : (List<Hoarder>) em.createQuery( "from Hoarder" ).getResultList() ) {
			hoarder.getItems().clear();
			em.remove( hoarder );
		}

		for ( Category category : (List<Category>) em.createQuery( "from Category" ).getResultList() ) {
			if ( category.getExampleItem() != null ) {
				category.setExampleItem( null );
				em.remove( category );
			}
		}

		for ( Item item : (List<Item>) em.createQuery( "from Item" ).getResultList() ) {
			item.setCategory( null );
			em.remove( item );
		}

		em.createQuery( "delete from Item" ).executeUpdate();

		em.getTransaction().commit();
		em.close();
	}
