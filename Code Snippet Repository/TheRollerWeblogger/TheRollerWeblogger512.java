    public WeblogEntriesPager getWeblogEntriesPager(String catArgument) {
        
        String anchor = previewRequest.getPreviewEntry();
        if(anchor == null) {
            anchor = previewRequest.getWeblogAnchor();
        }
        
        if (anchor != null) {
            return new WeblogEntriesPreviewPager(
                    urlStrategy,
                    previewRequest.getWeblog(),
                    previewRequest.getLocale(),
                    previewRequest.getWeblogPageName(),
                    anchor,
                    previewRequest.getWeblogDate(),
                    null,
                    previewRequest.getTags(),
                    previewRequest.getPageNum());
        } else {
            return new WeblogEntriesLatestPager(
                    urlStrategy,
                    previewRequest.getWeblog(),
                    previewRequest.getLocale(),
                    previewRequest.getWeblogPageName(),
                    previewRequest.getWeblogAnchor(),
                    previewRequest.getWeblogDate(),
                    null,
                    previewRequest.getTags(),
                    previewRequest.getPageNum());
        }
        
    }
