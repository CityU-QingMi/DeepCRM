    public int validate(WeblogEntryComment comment, RollerMessages messages) {
        Matcher m = linkPattern.matcher(comment.getContent());
        int count = 0;
        while (m.find()) {
            if (count++ > threshold) {
                messages.addError("comment.validator.excessLinksMessage", Integer.toString(threshold));
                return 0;
            }
        }
        return RollerConstants.PERCENT_100;
    }
