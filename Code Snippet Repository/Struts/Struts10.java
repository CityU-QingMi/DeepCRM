	public String delete() throws Exception {
		if (toDelete != null) {
			int count = 0;
			for (int i = 0, j = toDelete.length; i < j; i++) {
				count = count + getDao().delete(toDelete[i]);
			}
			if (log.isDebugEnabled()) {
				log.debug("AbstractCRUDAction - [delete]: " + count + " items deleted.");
			}
		}
		return SUCCESS;
	}
