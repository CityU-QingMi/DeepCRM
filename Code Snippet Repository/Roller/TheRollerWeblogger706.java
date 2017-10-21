    public Date getEndDate() {
        if (!StringUtils.isEmpty(getEndDateString())) {
            try {
                DateFormat df = new SimpleDateFormat("MM/dd/yy");
                Date day = df.parse(getEndDateString());
                return DateUtil.getEndOfDay(day);
            } catch (Exception e) {}
        }
        return null;
    }
