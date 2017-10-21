    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        JobDataMap data = context.getMergedJobDataMap();

        MailInfo mailInfo = populateMailInfo(data, createMailInfo());
        
        getLog().info("Sending message " + mailInfo);

        try {
            MimeMessage mimeMessage = prepareMimeMessage(mailInfo);
            
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new JobExecutionException("Unable to send mail: " + mailInfo,
                    e, false);
        }

    }
