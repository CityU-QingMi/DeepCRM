    public void doRun() {
        Date start = new Date();

        // since this operation can be run on a separate thread we must treat
        // the weblog object passed in as a detached object which is proned to
        // lazy initialization problems, so requery for the object now
        try {
            this.website = roller.getWeblogManager().getWeblog(
                    this.website.getId());
        } catch (WebloggerException ex) {
            mLogger.error("Error getting website object", ex);
            return;
        }

        IndexWriter writer = beginWriting();
        try {
            if (writer != null) {
                String handle = null;
                if (website != null) {
                    handle = website.getHandle();
                }
                Term tHandle = IndexUtil.getTerm(FieldConstants.WEBSITE_HANDLE,
                        handle);

                if (tHandle != null) {
                    writer.deleteDocuments(tHandle);
                }
            }
        } catch (IOException e) {
            mLogger.info("Problems deleting doc from index", e);
        } finally {
            endWriting();
        }

        Date end = new Date();
        double length = (end.getTime() - start.getTime()) / (double) RollerConstants.SEC_IN_MS;

        if (website != null) {
            mLogger.info("Completed deleting indices for website '"
                    + website.getName() + "' in '" + length + "' seconds");
        }
    }
