	private void verifyResourceBundle(String bundle) throws Exception
	{
		// verify user-dir; should end with roller
		//assertNotNull(userDir);
		//assertTrue(userDir.endsWith("roller"));
		
		// load Chinese resource file
		Properties props = new Properties();
		props.load(
			new FileInputStream(
				   System.getProperty("project.build.directory")
                    + "/classes/"
					+ bundle
					+ ".properties"));

		Set keys = baseProps.keySet();
		boolean missingMessage = false;

		// check Chinese
		System.out.println("Verifying " + bundle + "...");
        for (Object key : baseProps.keySet()) {
            if (props.getProperty((String) key) == null)
            {
                System.err.println(key + " = " + baseProps.getProperty((String) key));
                missingMessage = true;
            }
        }

		assertFalse(missingMessage);
	}
