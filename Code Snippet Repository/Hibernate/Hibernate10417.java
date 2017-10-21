		private AuditJoinTableInfo(
				String name, SequenceIdRevisionEntity rev,
				RevisionType revType, String joinColumnName, Long joinColumnId,
				String inverseJoinColumnName, Long inverseJoinColumnId) {
			this.name = name;
			this.revId = rev.getId();
			this.revType = revType;
			this.joinColumnName = joinColumnName;
			this.joinColumnId = joinColumnId;
			this.inverseJoinColumnName = inverseJoinColumnName;
			this.inverseJoinColumnId = inverseJoinColumnId;
		}
