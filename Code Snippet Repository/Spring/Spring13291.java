	@Before
	public void setup() {
		this.locations = new ArrayList<>();
		this.locations.add(new ClassPathResource("test/", getClass()));
		this.locations.add(new ClassPathResource("testalternatepath/", getClass()));

		this.resolver = new VersionResourceResolver();
		this.chain = mock(ResourceResolverChain.class);
		this.versionStrategy = mock(VersionStrategy.class);
	}
