    public void setAsNoOp() {

//        memoryRow       = null;
        session         = null;
        actionTimestamp = 0;
        commitTimestamp = 0;
        rolledback      = false;
        deleteComplete  = false;
        changeColumnMap = null;
        prepared        = false;
        type            = ACTION_NONE;
        next            = null;
    }
