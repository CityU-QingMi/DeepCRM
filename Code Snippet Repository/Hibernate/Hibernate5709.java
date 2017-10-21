	@Before
	public void setUp() throws Exception {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
									 projects = new Projects();
									 projects.addPreviousProject( new Project( "First" ) );
									 projects.addPreviousProject( new Project( "Second" ) );
									 projects.setCurrentProject( new Project( "Third" ) );

									 ContactDetail contactDetail = new ContactDetail();
									 contactDetail.setEmail( "abc@mail.org" );
									 contactDetail.addPhone( new Phone( "+4411111111" ) );

									 final Manager manager = new Manager();
									 manager.setProjects( projects );
									 manager.setContactDetail( contactDetail );
									 entityManager.persist( manager );

									 final Leader leader = new Leader();
									 leader.setInformation( new Information() );
									 ContactDetail infoContactDetail = new ContactDetail();
									 infoContactDetail.setEmail( "xyz@mail.org" );
									 infoContactDetail.addPhone( new Phone( "999-999-9999" ) );
									 leader.getInformation().setInfoContactDetail( infoContactDetail );
									 entityManager.persist( leader );
								 }
		);
	}
