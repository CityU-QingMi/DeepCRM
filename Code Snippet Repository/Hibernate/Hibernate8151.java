		private void prepare() {
			Session s = openSession();
			Transaction txn = s.beginTransaction();

			Animal mother = new Animal();
			mother.setDescription( "root-1" );

			Animal another = new Animal();
			another.setDescription( "root-2" );

			Animal son = new Animal();
			son.setDescription( "son");

			Animal daughter = new Animal();
			daughter.setDescription( "daughter" );

			Animal grandson = new Animal();
			grandson.setDescription( "grandson" );

			Animal grandDaughter = new Animal();
			grandDaughter.setDescription( "granddaughter" );

			son.setMother( mother );
			mother.addOffspring( son );

			daughter.setMother( mother );
			mother.addOffspring( daughter );

			grandson.setMother( daughter );
			daughter.addOffspring( grandson );

			grandDaughter.setMother( daughter );
			daughter.addOffspring( grandDaughter );

			s.save( mother );
			s.save( another );
			s.save( son );
			s.save( daughter );
			s.save( grandson );
			s.save( grandDaughter );

			txn.commit();
			s.close();

			root1Id = mother.getId();
			root2Id = another.getId();
		}
