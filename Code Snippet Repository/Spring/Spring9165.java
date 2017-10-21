	@Override
	public void runUnderUOW(int type, boolean join, UOWAction action) throws UOWActionException, UOWException {
		this.type = type;
		this.joined = join;
		try {
			this.status = UOW_STATUS_ACTIVE;
			action.run();
			this.status = (this.rollbackOnly ? UOW_STATUS_ROLLEDBACK : UOW_STATUS_COMMITTED);
		}
		catch (Error err) {
			this.status = UOW_STATUS_ROLLEDBACK;
			throw err;
		}
		catch (RuntimeException ex) {
			this.status = UOW_STATUS_ROLLEDBACK;
			throw ex;
		}
		catch (Exception ex) {
			this.status = UOW_STATUS_ROLLEDBACK;
			throw new UOWActionException(ex);
		}
	}
