	@SuppressWarnings( {""})
	private void cleanup() {
		Session s = openSession();
		s.beginTransaction();

		s.createQuery( "delete from SubItem" ).executeUpdate();
		for ( Hoarder hoarder : (List<Hoarder>) s.createQuery( "from Hoarder" ).list() ) {
			hoarder.getItems().clear();
			s.delete( hoarder );
		}

		for ( Category category : (List<Category>) s.createQuery( "from Category" ).list() ) {
			Item exampleItem = category.getExampleItem();
			if ( exampleItem != null ) {
				category.setExampleItem( null );
				exampleItem.setCategory( null );
				s.delete( category );
				s.delete (exampleItem );
			}
		}

		for ( Item item : (List<Item>) s.createQuery( "from Item" ).list() ) {
			Category category = item.getCategory();
			item.setCategory( null );
			if ( category != null ) {
				category.setExampleItem( null );
			}
			s.delete( item );
		}

		s.createQuery( "delete from Item" ).executeUpdate();


		s.getTransaction().commit();
		s.close();
	}
