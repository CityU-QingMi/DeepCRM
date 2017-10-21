    public TabularData getAllJobDetails() throws Exception {
        try {
            List<JobDetail> detailList = new ArrayList<JobDetail>();
            for (String jobGroupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName))) {
                    detailList.add(scheduler.getJobDetail(jobKey));
                }
            }
            return JobDetailSupport.toTabularData(detailList.toArray(new JobDetail[detailList.size()]));
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
