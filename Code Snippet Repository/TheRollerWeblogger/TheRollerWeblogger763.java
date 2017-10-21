    public void myPrepare() {

        if (getIdSelections() != null) {

            // query for templates list
            try {

                WeblogManager mgr = WebloggerFactory.getWeblogger()
                        .getWeblogManager();

                List<WeblogTemplate> pages = new ArrayList<WeblogTemplate>();
                WeblogTemplate template = null;

                String[] idsToDelete = getIdSelections();
                if (idsToDelete != null && idsToDelete.length > 0) {

                    for (String id : idsToDelete) {
                        if (!id.equals("")) {
                            template = mgr.getTemplate(id);
                            if (template != null) {
                                pages.add(template);
                            }
                        }
                    }

                }

                // Set page data
                setTemplates(pages);
                setIds(Utilities.stringArrayToString(idsToDelete, ","));

                // Flush for operation
                WebloggerFactory.getWeblogger().flush();

            } catch (Exception ex) {
                log.error("Error getting templates for weblog - "
                        + getActionWeblog().getHandle(), ex);
                addError("error.unexpected");
            }

        }
    }
