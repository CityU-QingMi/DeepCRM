    private int getRollbackType(Session session) {

        int           actionType = ACTION_NONE;
        RowActionBase action     = this;

        do {
            if (action.session == session && action.rolledback) {
                if (action.type == ACTION_DELETE) {
                    if (actionType == ACTION_INSERT) {
                        actionType = ACTION_INSERT_DELETE;
                    } else {
                        actionType = action.type;
                    }
                } else if (action.type == ACTION_INSERT) {
                    actionType = action.type;
                }
            }

            action = action.next;
        } while (action != null);

        return actionType;
    }
