    public String search() {

        boolean valSuccess = myValidate();

        if (valSuccess) {
            MediaFileFilter filter = new MediaFileFilter();
            bean.copyTo(filter);
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            try {
                List<MediaFile> rawResults = manager.searchMediaFiles(
                        getActionWeblog(), filter);
                boolean hasMore = false;
                List<MediaFile> results = new ArrayList<MediaFile>();
                results.addAll(rawResults);
                if (results.size() > MediaFileSearchBean.PAGE_SIZE) {
                    results.remove(results.size() - 1);
                    hasMore = true;
                }

                this.pager = new MediaFilePager(bean.getPageNum(), results,
                        hasMore);

            } catch (Exception e) {
                log.error("Error applying search criteria", e);
                addError("Error applying search criteria - check Roller logs");
            }

        }

        return SUCCESS;
    }
