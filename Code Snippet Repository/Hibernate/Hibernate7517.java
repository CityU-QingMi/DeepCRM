	@Override
	protected void prepareTest() throws Exception {
		doInHibernate( this::sessionFactory, session -> {

			// make 5 cats with 3 kittens each
			for ( int i = 0; i < 5; i++ ) {
				Cat cat = new Cat();
				cat.catId = i;
				cat.name = "cat_" + i;
				session.save( cat );
				for ( int j = 0; j < 3; j++ ) {
					Kitten k = new Kitten();
					k.kittenId = 5 * i + j;
					k.name = "kitty_" + i + "_" + j;
					k.cat = cat;
					cat.kittens.add( k );
					session.save( k );
				}
			}

			session.flush();
			session.clear();
		} );
	}
