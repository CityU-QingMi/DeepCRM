    static void performIntegrityChecks(Session session, Table table,
                                       Object[] oldData, Object[] newData,
                                       int[] updatedColumns) {

        if (newData == null) {
            return;
        }

        for (int i = 0, size = table.checkConstraints.length; i < size; i++) {
            table.checkConstraints[i].checkInsert(session, table, newData,
                                                  oldData == null);
        }

        if (!session.database.isReferentialIntegrity()) {
            return;
        }

        for (int i = 0, size = table.fkConstraints.length; i < size; i++) {
            boolean    check = oldData == null;
            Constraint c     = table.fkConstraints[i];

            if (!check) {
                check = ArrayUtil.haveCommonElement(c.getRefColumns(),
                                                    updatedColumns);
            }

            if (check) {
                c.checkInsert(session, table, newData, oldData == null);
            }
        }
    }
