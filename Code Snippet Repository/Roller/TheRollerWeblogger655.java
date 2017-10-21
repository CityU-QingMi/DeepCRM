    public void myPrepare() {
        PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();

        if (!StringUtils.isEmpty(getBean().getId())) {
            // edit case
            try {
                pingTarget = pingTargetMgr.getPingTarget(getBean().getId());
            } catch (WebloggerException ex) {
                log.error("Error looking up ping target - " + getBean().getId());
            }
            if (pingTarget == null) {
                addError("pingTarget.notFound", getBean().getId());
            }
        } else {
            // add case
            pingTarget = new PingTarget();
            pingTarget.setConditionCode(PingTarget.CONDITION_OK);
            pingTarget.setAutoEnabled(false);
        }
    }
