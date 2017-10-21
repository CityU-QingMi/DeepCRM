	@Test
	public void testMerge() {
		Session s = openSession();
		s.beginTransaction();

		Route route = new Route();
		route.setName( "routeA" );

		s.save( route );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		route = (Route) s.get( Route.class, route.getRouteID() );

		route.setTransientField( new String( "sfnaouisrbn" ) );

		Tour tour = new Tour();
		tour.setName( "tourB" );

		Node pickupNode = new Node();
		pickupNode.setName( "pickupNodeB" );

		Node deliveryNode = new Node();
		deliveryNode.setName( "deliveryNodeB" );

		pickupNode.setRoute( route );
		pickupNode.setTour( tour );
		pickupNode.setTransientField( "pickup node aaaaaaaaaaa" );

		deliveryNode.setRoute( route );
		deliveryNode.setTour( tour );
		deliveryNode.setTransientField( "delivery node aaaaaaaaa" );

		tour.getNodes().add( pickupNode );
		tour.getNodes().add( deliveryNode );

		route.getNodes().add( pickupNode );
		route.getNodes().add( deliveryNode );

		Route mergedRoute = (Route) s.merge( route );

		s.getTransaction().commit();
		s.close();
	}
