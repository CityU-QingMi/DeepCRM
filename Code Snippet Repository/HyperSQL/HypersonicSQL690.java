    synchronized void complete(Session session) {

        RowActionBase action;

        action = this;

        do {
            if (action.session == session) {
                if (action.actionTimestamp == 0) {
                    action.actionTimestamp = session.actionTimestamp;
                }
            }

            action = action.next;
        } while (action != null);
    }
