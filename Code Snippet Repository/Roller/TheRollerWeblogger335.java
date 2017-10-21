    public String save() {
        
        myValidate();
        
        if (!hasActionErrors()) {
            try {
                PlanetGroup planetGroup = getGroup();
                if(planetGroup == null) {
                    log.debug("Adding New Group");
                    planetGroup = new PlanetGroup();
                    planetGroup.setPlanet(getPlanet());
                } else {
                    log.debug("Updating Existing Group");
                }

                // copy in submitted data
                getBean().copyTo(planetGroup);

                // save and flush
                PlanetManager pmgr = WebloggerFactory.getWeblogger().getPlanetManager();
                pmgr.saveGroup(planetGroup);
                WebloggerFactory.getWeblogger().flush();

                addMessage("planetGroups.success.saved");

            } catch(Exception ex) {
                log.error("Error saving planet group - " + getBean().getId(), ex);
                addError("planetGroups.error.saved");
            }
        }
        
        return LIST;
    }
