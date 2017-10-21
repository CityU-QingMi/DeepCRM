    public List<String> getJobNames(String groupName) throws Exception {
        try {
            List<String> jobNames = new ArrayList<String>();
            for(JobKey key: scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                jobNames.add(key.getName());
            }
            return jobNames;
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
