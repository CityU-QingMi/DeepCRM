    private String buildBaseUrl() {

        Map<String, String> params = new HashMap<String, String>();

        if (!StringUtils.isEmpty(getBean().getEntryId())) {
            params.put("bean.entryId", getBean().getEntryId());
        }
        if (!StringUtils.isEmpty(getBean().getSearchString())) {
            params.put("bean.searchString", getBean().getSearchString());
        }
        if (!StringUtils.isEmpty(getBean().getStartDateString())) {
            params.put("bean.startDateString", getBean().getStartDateString());
        }
        if (!StringUtils.isEmpty(getBean().getEndDateString())) {
            params.put("bean.endDateString", getBean().getEndDateString());
        }
        if (!StringUtils.isEmpty(getBean().getApprovedString())) {
            params.put("bean.approvedString", getBean().getApprovedString());
        }

        return WebloggerFactory
                .getWeblogger()
                .getUrlStrategy()
                .getActionURL("comments", "/roller-ui/authoring",
                        getActionWeblog().getHandle(), params, false);
    }
