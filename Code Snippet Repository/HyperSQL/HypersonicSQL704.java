    synchronized int commit(Session session) {

        RowActionBase action     = this;
        int           actiontype = ACTION_NONE;

        do {
            if (action.session == session && action.commitTimestamp == 0) {
                action.commitTimestamp = session.actionTimestamp;
                action.prepared        = false;

                if (action.type == ACTION_INSERT) {
                    actiontype = action.type;
                } else if (action.type == ACTION_DELETE) {
                    if (actiontype == ACTION_INSERT) {

                        // ACTION_INSERT + ACTION_DELETE
                        actiontype = ACTION_INSERT_DELETE;
                    } else {
                        actiontype = action.type;
                    }
                }
            }

            action = action.next;
        } while (action != null);

        return actiontype;
    }
