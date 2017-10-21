    public List<KeyValueObject> getCommentStatusOptions() {

        List<KeyValueObject> opts = new ArrayList<KeyValueObject>();

        opts.add(new KeyValueObject("ALL", getText("generic.all")));
        opts.add(new KeyValueObject("ONLY_PENDING",
                getText("commentManagement.onlyPending")));
        opts.add(new KeyValueObject("ONLY_APPROVED",
                getText("commentManagement.onlyApproved")));
        opts.add(new KeyValueObject("ONLY_DISAPPROVED",
                getText("commentManagement.onlyDisapproved")));
        opts.add(new KeyValueObject("ONLY_SPAM",
                getText("commentManagement.onlySpam")));

        return opts;
    }
