	@Before
	public void setUp() throws Exception {
		props = new Properties();

		props.setProperty( "my.nonexistent.prop", "${}" );

		props.setProperty( "my.string.prop", "${test.my.sys.string.prop}" );
		System.setProperty( "test.my.sys.string.prop", "string" );

		props.setProperty( "my.boolean.prop", "${test.my.sys.boolean.prop}" );
		System.setProperty( "test.my.sys.boolean.prop", "true" );

		props.setProperty( "my.int.prop", "${test.my.sys.int.prop}" );
		System.setProperty( "test.my.sys.int.prop", "1" );

		props.setProperty( "my.integer.prop", "${test.my.sys.integer.prop}" );
		System.setProperty( "test.my.sys.integer.prop", "1" );

		props.setProperty( "partial.prop1", "${somedir}/middle/dir/${somefile}" );
		props.setProperty( "partial.prop2", "basedir/${somedir}/myfile.txt" );
		System.setProperty( "somedir", "tmp" );
		System.setProperty( "somefile", "tmp.txt" );

		props.setProperty( "parse.error", "steve" );
	}
