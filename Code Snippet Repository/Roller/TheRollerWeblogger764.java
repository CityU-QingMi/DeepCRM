    public String execute() {

        if (getIds() != null && getTemplates() != null
                && getTemplates().size() > 0) {
            return "confirm";
        } else {
            return SUCCESS;
        }

    }
