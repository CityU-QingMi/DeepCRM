	@Override
	public void execute() throws BuildException {
		try {
			doExecution();
		}
		catch (BuildException e) {
			throw e;
		}
		catch (Exception e) {
			throw new BuildException( "Error performing export : " + e.getMessage(), e );
		}
	}
