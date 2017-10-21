    void getTriggerTableNames(OrderedHashSet set, boolean write) {

        for (int i = 0; i < baseTable.triggerList.length; i++) {
            TriggerDef td = baseTable.triggerList[i];

            switch (type) {

                case StatementTypes.INSERT :
                    if (td.getStatementType() == StatementTypes.INSERT) {
                        break;
                    }

                    continue;
                case StatementTypes.UPDATE_WHERE :
                    if (td.getStatementType() == StatementTypes.UPDATE_WHERE) {
                        break;
                    }

                    continue;
                case StatementTypes.DELETE_WHERE :
                    if (td.getStatementType() == StatementTypes.DELETE_WHERE) {
                        break;
                    }

                    continue;
                case StatementTypes.MERGE :
                    if (td.getStatementType() == StatementTypes.INSERT
                            || td.getStatementType()
                               == StatementTypes.UPDATE_WHERE) {
                        break;
                    }

                    continue;
                default :
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "StatementDML");
            }

            if (td.routine != null) {
                if (write) {
                    set.addAll(td.routine.getTableNamesForWrite());
                } else {
                    set.addAll(td.routine.getTableNamesForRead());
                }
            }
        }
    }
