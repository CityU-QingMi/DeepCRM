    private void setAsDeleteFinal(long timestamp) {

        actionTimestamp = 0;
        commitTimestamp = timestamp;
        rolledback      = false;
        deleteComplete  = false;
        prepared        = false;
        changeColumnMap = null;
        type            = ACTION_DELETE_FINAL;
        next            = null;
    }
