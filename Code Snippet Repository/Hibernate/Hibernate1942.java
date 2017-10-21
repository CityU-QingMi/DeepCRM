	private static EntityEntry deserializeEntityEntry(char[] entityEntryClassNameArr, ObjectInputStream ois, StatefulPersistenceContext rtn){
		EntityEntry entry = null;

		final String entityEntryClassName = new String( entityEntryClassNameArr );
		final Class entityEntryClass =   rtn.getSession().getFactory().getServiceRegistry().getService( ClassLoaderService.class ).classForName( entityEntryClassName );

		try {
			final Method deserializeMethod = entityEntryClass.getDeclaredMethod( "deserialize", ObjectInputStream.class,	PersistenceContext.class );
			entry = (EntityEntry) deserializeMethod.invoke( null, ois, rtn );
		}
		catch (NoSuchMethodException e) {
			log.errorf( "Enable to deserialize [%s]", entityEntryClassName );
		}
		catch (InvocationTargetException e) {
			log.errorf( "Enable to deserialize [%s]", entityEntryClassName );
		}
		catch (IllegalAccessException e) {
			log.errorf( "Enable to deserialize [%s]", entityEntryClassName );
		}

		return entry;

	}
