	private void modifyEntity(A a) {
		// create a *circular* graph in detached entity
		a.setData( "Anthony" );

		G g = new G();
		g.setData( "Giovanni" );

		H h = new H();
		h.setData( "Hellen" );

		a.setG( g );
		g.setA( a );

		a.getHs().add( h );
		h.setA( a );

		g.getHs().add( h );
		h.getGs().add( g );
	}
