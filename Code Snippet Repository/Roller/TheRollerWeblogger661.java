    public String deleteConfirm() {

        if(getPingTarget() != null) {
            setPageTitle("pingTarget.confirmRemoveTitle");

            return "confirm";
        } else {
            addError("pingTarget.notFound",getPingTargetId());
        }

        return LIST;
    }
