    public String save() {
        myValidate();

        if (!hasActionErrors()) {
            try {
                // copy data from form into ping target
                getBean().copyTo(pingTarget);
                PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
                pingTargetMgr.savePingTarget(pingTarget);
                WebloggerFactory.getWeblogger().flush();

                addMessage(isAdd() ? "pingTarget.created" : "pingTarget.updated",
                        pingTarget.getName());

                return SUCCESS;
            } catch (WebloggerException ex) {
                log.error("Error adding/editing ping target", ex);
                addError("generic.error.check.logs");
            }
        }

        return INPUT;
    }
