	public IteratorImpl(
			ResultSet rs,
			PreparedStatement ps,
			EventSource sess,
			boolean readOnly,
			Type[] types,
			String[][] columnNames,
			HolderInstantiator holderInstantiator) throws HibernateException, SQLException {
		this.rs = rs;
		this.ps = ps;
		this.session = sess;
		this.readOnly = readOnly;
		this.types = types;
		this.names = columnNames;
		this.holderInstantiator = holderInstantiator;

		single = types.length == 1;

		postNext();
	}
