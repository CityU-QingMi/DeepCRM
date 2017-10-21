    protected void configureSendMailJob(JobDetail job) {
        JobDataMap jobData = job.getJobDataMap();
        jobData.put(SendMailJob.PROP_SMTP_HOST, "localhost");
        jobData.put(SendMailJob.PROP_SENDER, sender);
        jobData.put(SendMailJob.PROP_RECIPIENT, "receiver@host.com");
        jobData.put(SendMailJob.PROP_SUBJECT, "test subject");
        jobData.put(SendMailJob.PROP_MESSAGE, "do not reply");
        jobData.put(SendMailJob.PROP_USERNAME, username);
        jobData.put(SendMailJob.PROP_PASSWORD, password);
        jobData.put("mail.smtp.port", "2500");
        jobData.put("mail.smtp.auth", "true");
    }
