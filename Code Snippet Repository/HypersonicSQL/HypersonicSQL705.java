    void setAsAction(RowActionBase action) {

        next            = action.next;
        session         = action.session;
        actionTimestamp = action.actionTimestamp;
        commitTimestamp = action.commitTimestamp;
        type            = action.type;
        deleteComplete  = action.deleteComplete;
        rolledback      = action.rolledback;
        prepared        = action.prepared;
        changeColumnMap = action.changeColumnMap;
    }
