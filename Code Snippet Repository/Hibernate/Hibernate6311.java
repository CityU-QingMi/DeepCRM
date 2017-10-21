	@Test
	@TestForIssue( jiraKey = "" )
	public void testInvalidReferenceToQuotedTableName() {
    	try {
    		Configuration config = new Configuration();
    		config.addAnnotatedClass(Printer.class);
    		config.addAnnotatedClass(PrinterCable.class);
    		sessionFactory = config.buildSessionFactory( serviceRegistry );
    		fail("expected MappingException to be thrown");
    	}
    	//we WANT MappingException to be thrown
        catch( MappingException e ) {
        	assertTrue("MappingException was thrown", true);
        }
        catch(Exception e) {
        	StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
            log.debug(writer.toString());
        	fail(e.getMessage());
        } finally {
			if(sessionFactory!=null){
				sessionFactory.close();
				sessionFactory = null;
			}
		}
	}
