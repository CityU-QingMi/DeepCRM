    synchronized int mergeRollback(Session session, long timestamp, Row row) {

        RowActionBase action         = this;
        RowActionBase head           = null;
        RowActionBase tail           = null;
        int           rollbackAction = getRollbackType(session);

        do {
            if (action.session == session && action.rolledback) {
                if (tail != null) {
                    tail.next = null;
                }
            } else {
                if (head == null) {
                    head = tail = action;
                } else {
                    tail.next = action;
                    tail      = action;
                }
            }

            action = action.next;
        } while (action != null);

        if (head == null) {
            switch (rollbackAction) {

                case ACTION_INSERT :
                case ACTION_INSERT_DELETE :
                    setAsDeleteFinal(timestamp);
                    break;

                case ACTION_DELETE :
                case ACTION_NONE :
                default :
                    setAsNoOp();
                    break;
            }
        } else {
            if (head != this) {
                setAsAction(head);
            }
        }

        return rollbackAction;
    }
