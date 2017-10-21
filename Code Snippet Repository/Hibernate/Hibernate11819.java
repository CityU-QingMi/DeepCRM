	@Configuration
	public Option[] config() throws Exception {
		final Properties paxExamEnvironment = loadPaxExamEnvironmentProperties();

		final boolean debug = ConfigurationHelper.getBoolean(
				"org.hibernate.testing.osgi.paxExam.debug",
				Environment.getProperties(),
				DEBUG
		);

		return options(
				when( debug ).useOptions( debugConfiguration( "5005", true ) ),
				karafDistributionConfiguration()
						.frameworkUrl( paxExamEnvironment.getProperty( "org.ops4j.pax.exam.container.karaf.distroUrl" ) )
						.karafVersion( paxExamEnvironment.getProperty( "org.ops4j.pax.exam.container.karaf.version" ) )
						.name( "Apache Karaf" )
						.unpackDirectory(
								new File(
										paxExamEnvironment.getProperty(
												"org.ops4j.pax.exam.container.karaf.unpackDir"
										)
								)
						)
						.useDeployFolder( false ),
				editConfigurationFileExtend(
						"etc/org.ops4j.pax.url.mvn.cfg",
						"org.ops4j.pax.url.mvn.repositories",
						"https://repository.jboss.org/nexus/content/groups/public/"
				),
				configureConsole().ignoreLocalConsole().ignoreRemoteShell(),
				when( debug ).useOptions( keepRuntimeFolder() ),
				logLevel( LogLevelOption.LogLevel.INFO ),
				features( featureXmlUrl( paxExamEnvironment ), "hibernate-orm" ),
				features( featureXmlUrl( paxExamEnvironment ), "hibernate-envers" ),
				features( testingFeatureXmlUrl(), "hibernate-osgi-testing" )
		);
	}
