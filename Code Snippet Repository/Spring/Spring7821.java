	@Override
	public DataSource getDataSource() {
		if (this.persistenceUnitInfo != null) {
			return (this.persistenceUnitInfo.getJtaDataSource() != null ?
					this.persistenceUnitInfo.getJtaDataSource() :
					this.persistenceUnitInfo.getNonJtaDataSource());
		}
		return (this.internalPersistenceUnitManager.getDefaultJtaDataSource() != null ?
				this.internalPersistenceUnitManager.getDefaultJtaDataSource() :
				this.internalPersistenceUnitManager.getDefaultDataSource());
	}
