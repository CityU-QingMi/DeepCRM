    @SkipValidation
    public String execute() {
        if (isAdd()) {
            // initial user create
            getBean().setLocale(Locale.getDefault().toString());
            getBean().setTimeZone(TimeZone.getDefault().getID());
        } else {
            // populate form data from user profile data
            getBean().copyFrom(user);
        }
        return INPUT;
    }
