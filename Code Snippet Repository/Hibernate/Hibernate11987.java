	@Override
	public void run() {
		isDone = false;
		error = null;
		try {
			executable.execute();
		}
		catch (Throwable t) {
			error = t;
		}
		finally {
			isDone = true;
		}
	}
