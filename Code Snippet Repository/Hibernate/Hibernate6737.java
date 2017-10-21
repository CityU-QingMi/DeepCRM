	@Test
	public void testReferenceColumnWithBacktics() throws Exception {
		Session s=openSession();
		s.beginTransaction();
		SysGroupsOrm g=new SysGroupsOrm();
		SysUserOrm u=new SysUserOrm();
		u.setGroups( new ArrayList<SysGroupsOrm>() );
		u.getGroups().add( g );
		s.save( g );
		s.save( u );
		s.getTransaction().commit();
		s.close();
	}
