    @SkipValidation
    public String execute() {
        if (getActionName().equals("entryEdit")) {
            // load bean with pojo data
            getBean().copyFrom(getEntry(), getLocale());
        } else {
            // set weblog defaults
            getBean().setLocale(getActionWeblog().getLocale());
            getBean().setAllowComments(getActionWeblog().getDefaultAllowComments());
            getBean().setCommentDays(getActionWeblog().getDefaultCommentDays());
            // apply weblog default plugins
            if (getActionWeblog().getDefaultPlugins() != null) {
                getBean().setPlugins(
                        StringUtils.split(getActionWeblog().getDefaultPlugins(),
                                ","));
            }
        }

        return INPUT;
    }
