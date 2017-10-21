		public AlterTableStatement(
				StandardServiceRegistry ssr,
				String tableName,
				String fkConstraintName,
				String fkColumnName,
				String referenceTableName) {
			this.ssr = ssr;
			this.tableName = tableName;
			this.fkConstraintName = fkConstraintName;
			this.fkColumnName = fkColumnName;
			this.referenceTableName = referenceTableName;
		}
