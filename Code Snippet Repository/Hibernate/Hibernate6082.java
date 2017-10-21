	@Before
	public void setUp(){
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getTransaction().begin();
			for ( String title : GAME_TITLES ) {
				Game game = new Game( title );
				em.persist( game );
			}
			em.getTransaction().commit();
		}catch (Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw e;
		}finally {
			em.close();
		}
	}
