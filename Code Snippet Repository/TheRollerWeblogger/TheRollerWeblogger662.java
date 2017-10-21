    public String delete() {
        if(getPingTarget() != null) {
            try {
                PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
                pingTargetMgr.removePingTarget(getPingTarget());
                WebloggerFactory.getWeblogger().flush();

                // remove deleted target from list
                getPingTargets().remove(getPingTarget());
                addMessage("pingTarget.deleted", getPingTarget().getName());
            } catch (WebloggerException ex) {
                log.error("Error deleting ping target - " + getPingTargetId(), ex);
                addError("generic.error.check.logs", getPingTargetId());
            }
        } else {
            addError("pingTarget.notFound", getPingTargetId());
        }
        return LIST;
    }
