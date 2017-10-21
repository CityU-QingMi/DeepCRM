    public int validateComment(WeblogEntryComment comment, RollerMessages messages) {
        int total = 0;
        if (validators.size() > 0) {
            for (CommentValidator val : validators) {
                log.debug("Invoking comment validator "+val.getName());
                total += val.validate(comment, messages);
            }
            total = total / validators.size();
        } else {
            // When no validators: consider all comments valid
            total = RollerConstants.PERCENT_100;
        }
        return total;
    }
