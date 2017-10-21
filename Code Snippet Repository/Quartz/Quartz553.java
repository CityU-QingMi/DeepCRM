    public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher) {
        Set<JobKey> outList = null;
        synchronized (lock) {

            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            String compareToValue = matcher.getCompareToValue();

            switch(operator) {
                case EQUALS:
                    HashMap<JobKey, JobWrapper> grpMap = jobsByGroup.get(compareToValue);
                    if (grpMap != null) {
                        outList = new HashSet<JobKey>();

                        for (JobWrapper jw : grpMap.values()) {

                            if (jw != null) {
                                outList.add(jw.jobDetail.getKey());
                            }
                        }
                    }
                    break;

                default:
                    for (Map.Entry<String, HashMap<JobKey, JobWrapper>> entry : jobsByGroup.entrySet()) {
                        if(operator.evaluate(entry.getKey(), compareToValue) && entry.getValue() != null) {
                            if(outList == null) {
                                outList = new HashSet<JobKey>();
                            }
                            for (JobWrapper jobWrapper : entry.getValue().values()) {
                                if(jobWrapper != null) {
                                    outList.add(jobWrapper.jobDetail.getKey());
                                }
                            }
                        }
                    }
            }
        }

        return outList == null ? java.util.Collections.<JobKey>emptySet() : outList;
    }
