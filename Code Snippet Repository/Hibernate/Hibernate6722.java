	@Test
	@TestForIssue( jiraKey = "" )
	public void testManyToOneWithJoinTable() {
		//HHH-4250 : @ManyToOne - @OneToMany doesn't work with @Inheritance(strategy= InheritanceType.JOINED)
		Session s = openSession();
		Transaction tx = s.beginTransaction();
				
		Client c1 = new Client();
		c1.setFirstname("Firstname1");
		c1.setName("Name1");
		c1.setCode("1234");
		c1.setStreet("Street1");
		c1.setCity("City1");
		
		Account a1 = new Account();
		a1.setNumber("1000");
		a1.setBalance(5000.0);
		
		a1.addClient(c1);
		
		s.persist(c1);
		s.persist(a1);
	    
		s.flush();
		s.clear();
		
		c1 = (Client) s.load(Client.class, c1.getId());
		assertEquals( 5000.0, c1.getAccount().getBalance(), 0.01 );
		
		s.flush();
		s.clear();
		
		a1 = (Account) s.load(Account.class,a1.getId());
		Set<Client> clients = a1.getClients();
		assertEquals(1, clients.size());
		Iterator<Client> it = clients.iterator();
		c1 = it.next();
		assertEquals("Name1", c1.getName());
				
		tx.rollback();
		s.close();
	}
