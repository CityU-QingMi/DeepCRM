	public Fum(FumCompositeID id) throws SQLException, HibernateException {
		this.id = id;
		friends = new HashSet();
		FumCompositeID fid = new FumCompositeID();
		fid.setShort( (short) ( id.short_ + 33 ) );
		fid.setString( id.string_ + "dd" );
		Fum f = new Fum();
		f.id = fid;
		f.fum="FRIEND";
		friends.add(f);
	}
