    public boolean isDeleted() {

        RowActionBase action = this;

        do {
            if (action.commitTimestamp != 0) {
                if (action.type == ACTION_DELETE
                        || action.type == ACTION_DELETE_FINAL) {
                    return true;
                }
            }

            action = action.next;
        } while (action != null);

        return false;
    }
