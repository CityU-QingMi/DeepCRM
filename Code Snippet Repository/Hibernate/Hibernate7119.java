	private JaxbHbmHibernateMapping generateXml(boolean includeEntityMode)
			throws Exception {
		JaxbHbmHibernateMapping hm = new JaxbHbmHibernateMapping();
		JaxbHbmRootEntityType clazz = new JaxbHbmRootEntityType();
		JaxbHbmTuplizerType tuplizer = new JaxbHbmTuplizerType();
		tuplizer.setClazz( DynamicMapEntityTuplizer.class.getCanonicalName() );
		if ( includeEntityMode ) {
			tuplizer.setEntityMode( EntityMode.MAP );
		}
		clazz.getTuplizer().add( tuplizer );
		JaxbHbmSimpleIdType id = new JaxbHbmSimpleIdType();
		clazz.setId( id );
		hm.getClazz().add( clazz );
		return hm;
	}
