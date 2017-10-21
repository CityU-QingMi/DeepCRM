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

									 final Employee employee = new Employee();
									 employee.setProjects( projects );
									 employee.setContactDetail( contactDetail );
									 entityManager.persist( employee );

									 final Person person = new Person();
									 person.setInformation( new Information() );
									 ContactDetail infoContactDetail = new ContactDetail();
									 infoContactDetail.setEmail( "xyz@mail.org" );
									 infoContactDetail.addPhone( new Phone( "999-999-9999" ) );
									 person.getInformation().setInfoContactDetail( infoContactDetail );
									 entityManager.persist( person );
								 }
		);
	}
