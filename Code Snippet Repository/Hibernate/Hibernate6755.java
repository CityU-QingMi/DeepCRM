	@Test
	public void testPersist() {
		EntitySerialize entitySerialize = new EntitySerialize();

		entitySerialize.explicitLob = new ExplicitSerializable();
		entitySerialize.explicitLob.value = "explicitLob";
		entitySerialize.explicitLob.defaultValue = "defaultExplicitLob";

		entitySerialize.explicit = new ExplicitSerializable();
		entitySerialize.explicit.value = "explicit";

		entitySerialize.implicit = new ImplicitSerializable();
		entitySerialize.implicit.value = "implicit";

		entitySerialize.explicitOverridingImplicit = new ImplicitSerializable();
		entitySerialize.explicitOverridingImplicit.value = "explicitOverridingImplicit";

		Session session = openSession();
		session.getTransaction().begin();
		session.persist( entitySerialize );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();

		EntitySerialize persistedSerialize = (EntitySerialize) session.get( EntitySerialize.class, entitySerialize.id );
		assertEquals( "explicitLob", persistedSerialize.explicitLob.value );
		assertEquals( "explicit", persistedSerialize.explicit.value );
		assertEquals( "implicit", persistedSerialize.implicit.value );
		assertEquals( "explicitOverridingImplicit", persistedSerialize.explicitOverridingImplicit.value );

		assertEquals( "defaultExplicitLob", persistedSerialize.explicitLob.defaultValue );

		session.delete( persistedSerialize );

		session.getTransaction().commit();
		session.close();
	}
