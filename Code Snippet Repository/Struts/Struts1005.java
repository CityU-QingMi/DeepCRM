    @Override
    public String execute() {
        // from action's bundle
        System.out.println(getText("actionProperty"));

        // from default bundle
        System.out.println(getText("foo.range"));

        // nonexistant
        System.out.println(getText("non.existant"));

        return NONE;
    }
