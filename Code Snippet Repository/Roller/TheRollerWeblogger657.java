    protected void myValidate() {
        try {
            PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
            if (StringUtils.isEmpty(bean.getName())) {
                addError("pingTarget.nameMissing");
            } else {
                if (isAdd() || !pingTarget.getName().equals(bean.getName())) {
                    if (pingTargetMgr.targetNameExists(bean.getName())) {
                        addError("pingTarget.nameNotUnique");
                    }
                }
            }
            if (StringUtils.isEmpty(bean.getPingUrl())) {
                addError("pingTarget.pingUrlMissing");
            } else {
                if (!pingTargetMgr.isUrlWellFormed(bean.getPingUrl())) {
                    addError("pingTarget.malformedUrl");
                } else if (!pingTargetMgr.isHostnameKnown(bean.getPingUrl())) {
                    addError("pingTarget.unknownHost");
                }
            }
        } catch (WebloggerException ex) {
            log.error("Error validating ping target", ex);
            addError("generic.error.check.logs");
        }
    }
