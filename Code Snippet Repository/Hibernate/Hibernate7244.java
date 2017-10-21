    private void fillInitialData() {
        Tour tour = new Tour();
        tour.setName("Tour 1");

        Route route = new Route();
        route.setName("Route 1");

        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < 4; i++) {
            Node n = new Node();
            n.setName("Node " + i);
            n.setTour(tour);
            n.setRoute(route);
            nodes.add(n);
        }

        tour.setNodes(new HashSet<Node>(nodes));
        route.setNodes(new HashSet<Node>(Arrays.asList(nodes.get(0), nodes.get(1), nodes.get(2))));

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Car");
        route.setVehicles(new HashSet<Vehicle>(Arrays.asList(vehicle)));
        vehicle.setRoute(route);

        Transport $0to1 = new Transport();
        $0to1.setName("Transport 0 -> 1");
        $0to1.setPickupNode(nodes.get(0));
        $0to1.setDeliveryNode(nodes.get(1));
        $0to1.setVehicle(vehicle);

        Transport $1to2 = new Transport();
        $1to2.setName("Transport 1 -> 2");
        $1to2.setPickupNode(nodes.get(1));
        $1to2.setDeliveryNode(nodes.get(2));
        $1to2.setVehicle(vehicle);

        vehicle.setTransports(new HashSet<Transport>(Arrays.asList($0to1, $1to2)));

        Session s = openSession();
        s.beginTransaction();

        s.persist(tour);

        s.getTransaction().commit();
        s.close();
    }
