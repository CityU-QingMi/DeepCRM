	@After
	public void tearDown() throws Exception {
		try {
			unbind("UserTransaction", ctx);
			unbind("java:/TransactionManager", ctx);
			ctx.close();
			jndiServer.stop();
	  }
	  finally {
		  if ( serviceRegistry != null ) {
			  ServiceRegistryBuilder.destroy( serviceRegistry );
		  }
	  }
	}
