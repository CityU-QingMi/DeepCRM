    public ApprovalStatus getStatus() {
        if (approvedString.equals("ONLY_APPROVED")) {
            return ApprovalStatus.APPROVED;
        } else if (approvedString.equals("ONLY_DISAPPROVED")) {
            return ApprovalStatus.DISAPPROVED;
        } else if (approvedString.equals("ONLY_PENDING")) {
            return ApprovalStatus.PENDING;
        } else if (approvedString.equals("ONLY_SPAM")) {
            return ApprovalStatus.SPAM;
        } else {
            // shows *all* comments, regardless of status
            return null;
        }
    }
