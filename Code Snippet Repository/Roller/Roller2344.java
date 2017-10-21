    private String buildBaseUrl() {
        
        Map<String, String> params = new HashMap<String, String>();
        
        if(!StringUtils.isEmpty(getBean().getCategoryName())) {
            params.put("bean.categoryPath", getBean().getCategoryName());
        }
        if(!StringUtils.isEmpty(getBean().getTagsAsString())) {
            params.put("bean.tagsAsString", getBean().getTagsAsString());
        }
        if(!StringUtils.isEmpty(getBean().getText())) {
            params.put("bean.text", getBean().getText());
        }
        if(!StringUtils.isEmpty(getBean().getStartDateString())) {
            params.put("bean.startDateString", getBean().getStartDateString());
        }
        if(!StringUtils.isEmpty(getBean().getEndDateString())) {
            params.put("bean.endDateString", getBean().getEndDateString());
        }
        if(!StringUtils.isEmpty(getBean().getStatus())) {
            params.put("bean.status", getBean().getStatus());
        }
        if(getBean().getSortBy() != null) {
            params.put("bean.sortBy", getBean().getSortBy().toString());
        }

        return WebloggerFactory.getWeblogger().getUrlStrategy().getActionURL("entries", "/roller-ui/authoring", 
                getActionWeblog().getHandle(), params, false);
    }
