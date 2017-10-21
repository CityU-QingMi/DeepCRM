    synchronized int getCommitTypeOn(long timestamp) {

        RowActionBase action     = this;
        int           actionType = ACTION_NONE;

        do {
            if (action.commitTimestamp == timestamp) {
                if (action.type == ACTION_INSERT) {
                    actionType = action.type;
                } else if (action.type == ACTION_DELETE) {
                    if (actionType == ACTION_INSERT) {

                        // ACTION_INSERT + ACTION_DELETE
                        actionType = ACTION_INSERT_DELETE;
                    } else {
                        actionType = action.type;
                    }
                }
            }

            action = action.next;
        } while (action != null);

        return actionType;
    }
