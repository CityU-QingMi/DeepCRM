    public boolean hasCurrentRefAction() {

        RowActionBase action = this;

        do {
            if (action.type == ACTION_REF && action.commitTimestamp == 0) {
                return true;
            }

            action = action.next;
        } while (action != null);

        return false;
    }
