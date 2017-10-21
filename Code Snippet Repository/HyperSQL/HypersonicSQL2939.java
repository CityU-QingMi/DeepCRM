    public OrderedHashSet getColumnsForPrivilege(Table table, int type) {

        if (isFull) {
            return table.getColumnNameSet();
        }

        switch (type) {

            case GrantConstants.SELECT :
                return isFullSelect ? table.getColumnNameSet()
                                    : selectColumnSet == null ? emptySet
                                                              : selectColumnSet;

            case GrantConstants.INSERT :
                return isFullInsert ? table.getColumnNameSet()
                                    : insertColumnSet == null ? emptySet
                                                              : insertColumnSet;

            case GrantConstants.UPDATE :
                return isFullUpdate ? table.getColumnNameSet()
                                    : updateColumnSet == null ? emptySet
                                                              : updateColumnSet;

            case GrantConstants.REFERENCES :
                return isFullReferences ? table.getColumnNameSet()
                                        : referencesColumnSet == null
                                          ? emptySet
                                          : referencesColumnSet;

            case GrantConstants.TRIGGER :
                return isFullTrigger ? table.getColumnNameSet()
                                     : triggerColumnSet == null ? emptySet
                                                                : triggerColumnSet;
        }

        return emptySet;
    }
