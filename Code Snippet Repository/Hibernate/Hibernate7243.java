    @Test
    public void testAttachedChildInMerge() {
        fillInitialData();

        Session s = openSession();
        s.beginTransaction();

        Route route = (Route) s.createQuery("FROM Route WHERE name = :name").setString("name", "Route 1").uniqueResult();
        Node n2 = (Node) s.createQuery("FROM Node WHERE name = :name").setString("name", "Node 2").uniqueResult();
        Node n3 = (Node) s.createQuery("FROM Node WHERE name = :name").setString("name", "Node 3").uniqueResult();

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Bus");
        vehicle.setRoute(route);

        Transport $2to3 = new Transport();
        $2to3.setName("Transport 2 -> 3");
        $2to3.setPickupNode(n2); n2.getPickupTransports().add($2to3);
        $2to3.setDeliveryNode(n3); n3.getDeliveryTransports().add($2to3);
        $2to3.setVehicle(vehicle);

        vehicle.setTransports(new HashSet<Transport>(Arrays.asList($2to3)));

        // Try to save graph of transient entities (vehicle, transport) which contains attached entities (node2, node3)
        Vehicle managedVehicle = (Vehicle) s.merge(vehicle);
        checkNewVehicle(managedVehicle);

        s.flush();
        s.clear();

        assertEquals(3, s.createQuery("FROM Transport").list().size());
        assertEquals(2, s.createQuery("FROM Vehicle").list().size());
        assertEquals(4, s.createQuery("FROM Node").list().size());

        Vehicle newVehicle = (Vehicle) s.createQuery("FROM Vehicle WHERE name = :name").setParameter("name", "Bus").uniqueResult();
        checkNewVehicle(newVehicle);

        s.getTransaction().commit();
        s.close();
    }
