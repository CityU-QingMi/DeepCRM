    public void enforceRowConstraints(Session session, Object[] data) {

        for (int i = 0; i < columnCount; i++) {
            Type         type = colTypes[i];
            ColumnSchema column;

            if (hasDomainColumns && type.isDomainType()) {
                Constraint[] constraints =
                    type.userTypeModifier.getConstraints();

                column = getColumn(i);

                for (int j = 0; j < constraints.length; j++) {
                    constraints[j].checkCheckConstraint(session, this, column,
                                                        data[i]);
                }
            }

            if (colNotNull[i] && data[i] == null) {
                String     constraintName;
                Constraint c = getNotNullConstraintForColumn(i);

                if (c == null) {
                    if (ArrayUtil.find(getPrimaryKey(), i) > -1) {
                        c = getPrimaryConstraint();
                    }
                }

                constraintName = c == null ? ""
                                           : c.getName().name;
                column         = getColumn(i);

                String[] info = new String[] {
                    constraintName, tableName.statementName,
                    column.getName().statementName
                };

                throw Error.error(null, ErrorCode.X_23502,
                                  ErrorCode.COLUMN_CONSTRAINT, info);
            }
        }
    }
