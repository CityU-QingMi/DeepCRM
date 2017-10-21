	public boolean onSave(Session db) throws CallbackException {
		_string = "a string";
		_date = new Date(123);
		_timestamp = new Date( System.currentTimeMillis() );
		_integer = new Integer( -666 );
		_long = new Long( 696969696969696969l - count++ );
		_short = new Short("42");
		_float = new Float( 6666.66f );
		//_double = new Double( 1.33e-69 );  // this double is too big for the sap db jdbc driver
		_double = new Double( 1.12e-36 );
		_boolean = new Boolean(true);
		_byte = new Byte( (byte) 127 );
		_int = 2;
		_char = '@';
		_bytes = _string.getBytes();
		Struct s = new Struct();
		s.name="name";
		s.count = 69;
		blob = s;
		binary = ( _string + "yada yada yada" ).getBytes();
		custom = new String[] { "foo", "bar" };
		component = new FooComponent("foo", 12, new Date[] { _date, _timestamp, null, new Date() }, new FooComponent("bar", 666, new Date[] { new Date(123456l), null }, null ) );
		component.setGlarch( new Glarch() );
		dependent = new Fee();
		dependent.setFi( "belongs to foo # " + getKey() );
		theLocale = Locale.getDefault();
		return NO_VETO;
	}
