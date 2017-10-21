    private RowAction mergeExpiredRefActions() {

        if (updatedAction != null) {
            updatedAction = updatedAction.mergeExpiredRefActions();
        }

        if (hasCurrentRefAction()) {
            return this;
        }

        return updatedAction;
    }
