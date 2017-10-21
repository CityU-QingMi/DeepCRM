    public String save() {
        log.debug("Entering save()");

        if (getTemplate() == null) {
            addError("Unable to locate specified template");
            return LIST;
        }

        // validation
        myValidate();

        if (!hasActionErrors()) {
            try {

                WeblogTemplate templateToSave = getTemplate();
                getBean().copyTo(templateToSave);
                templateToSave.setLastModified(new Date());

                if (getBean().getAutoContentType() == null ||
                        !getBean().getAutoContentType()) {
                    templateToSave.setOutputContentType(getBean().getManualContentType());
                } else {
                    // empty content-type indicates that template uses auto content-type detection
                    templateToSave.setOutputContentType(null);
                }

                // save template
                WebloggerFactory.getWeblogger().getWeblogManager().saveTemplate(templateToSave);
                log.debug("Saved template: " + templateToSave.getId());

                //flush
                WebloggerFactory.getWeblogger().flush();

                // notify caches
                CacheManager.invalidate(templateToSave);

                // success message
                addMessage("pageForm.save.success", templateToSave.getName());

            } catch (Exception ex) {
                log.error("Error updating page - " + getBean().getId(), ex);
                addError("Error updating template - check Roller logs");
            }
        }

        log.debug("Leaving save()");
        return INPUT;
    }
