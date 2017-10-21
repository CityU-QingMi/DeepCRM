	@Test
	public void hibernateTest() {
		printConfig();
		
		Session session = openSession();
		session.beginTransaction();
		LobTestEntity entity = new LobTestEntity();
		entity.setId(1L);
		entity.setLobValue(session.getLobHelper().createBlob(new byte[9999]));
		entity.setQwerty(randomString(4000));
		session.save(entity);
		session.getTransaction().commit();
	}
