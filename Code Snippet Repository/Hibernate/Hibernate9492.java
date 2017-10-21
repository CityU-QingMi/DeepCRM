		public void createData(){
			Session session = openSession();
			Transaction tx = session.beginTransaction();
			Country usa = new Country();
			session.save( usa );
			list.add( usa );
			Org disney = new Org();
			disney.setCountry( usa );
			session.save( disney );
			list.add( disney );
			Contact waltDisney = new Contact();
			waltDisney.setOrg( disney );
			session.save( waltDisney );
			list.add( waltDisney );
			tx.commit();
			session.close();
		}
