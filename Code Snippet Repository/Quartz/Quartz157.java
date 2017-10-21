    public void addJob(Map<String, Object> abstractJobInfo,    boolean replace) throws Exception {
        try {
            String jobDetailClassName = (String) abstractJobInfo.remove("jobDetailClass");
            if(jobDetailClassName == null) {
                throw new IllegalArgumentException("No jobDetailClass specified");
            }
            Class<?> jobDetailClass = Class.forName(jobDetailClassName);
            JobDetail jobDetail = (JobDetail) jobDetailClass.newInstance();
            
            String jobClassName = (String) abstractJobInfo.remove("jobClass");
            if(jobClassName == null) {
                throw new IllegalArgumentException("No jobClass specified");
            }
            Class<?> jobClass = Class.forName(jobClassName);
            abstractJobInfo.put("jobClass", jobClass);
            
            for(Map.Entry<String, Object> entry : abstractJobInfo.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if("jobDataMap".equals(key)) {
                    value = new JobDataMap((Map<?, ?>)value);
                }
                invokeSetter(jobDetail, key, value);
            }
    
            scheduler.addJob(jobDetail, replace);
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
