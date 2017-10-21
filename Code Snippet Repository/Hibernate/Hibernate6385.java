	@Test
	@TestForIssue( jiraKey = "" )
	public void testLoadEntityWithEagerFetchingToKeyManyToOneReferenceBackToSelf() {
		// based on the core testsuite test of same name in org.hibernate.test.keymanytoone.bidir.component.EagerKeyManyToOneTest
		// meant to test against regression relating to http://opensource.atlassian.com/projects/hibernate/browse/HHH-2277
		// and http://opensource.atlassian.com/projects/hibernate/browse/HHH-4147

		{
			Session s = openSession();
			s.beginTransaction();
			Card card = new Card( CARD_ID );
			Key key = new Key( KEY_ID );
			card.addField( card, key );
			s.persist( key );
			s.persist( card );
			s.getTransaction().commit();
			s.close();
		}

		{
			Session s = openSession();
			s.beginTransaction();
			try {
				Card card = (Card) s.get( Card.class, CARD_ID );
				assertEquals( 1, card.getFields().size() );
				CardField cf = card.getFields().iterator().next();
				assertSame( card, cf.getPrimaryKey().getCard() );
			}
			catch ( StackOverflowError soe ) {
				fail( "eager + key-many-to-one caused stack-overflow in annotations" );
			}
			finally {
				s.getTransaction().commit();
				s.close();
			}
		}

		{
			Session s = openSession();
			s.beginTransaction();
			Card card = (Card) s.get( Card.class, CARD_ID );
			Key key = (Key) s.get( Key.class, KEY_ID );
			s.delete( card );
			s.delete( key );
			s.getTransaction().commit();
			s.close();
		}
	}
