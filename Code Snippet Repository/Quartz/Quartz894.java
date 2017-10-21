    protected MailInfo populateMailInfo(JobDataMap data, MailInfo mailInfo) {
        // Required parameters
        mailInfo.setSmtpHost(getRequiredParm(data, PROP_SMTP_HOST, "PROP_SMTP_HOST"));
        mailInfo.setTo(getRequiredParm(data, PROP_RECIPIENT, "PROP_RECIPIENT"));
        mailInfo.setFrom(getRequiredParm(data, PROP_SENDER, "PROP_SENDER"));
        mailInfo.setSubject(getRequiredParm(data, PROP_SUBJECT, "PROP_SUBJECT"));
        mailInfo.setMessage(getRequiredParm(data, PROP_MESSAGE, "PROP_MESSAGE"));
        
        // Optional parameters
        mailInfo.setReplyTo(getOptionalParm(data, PROP_REPLY_TO));
        mailInfo.setCc(getOptionalParm(data, PROP_CC_RECIPIENT));
        mailInfo.setContentType(getOptionalParm(data, PROP_CONTENT_TYPE));
        mailInfo.setUsername(getOptionalParm(data, PROP_USERNAME));
        mailInfo.setPassword(getOptionalParm(data, PROP_PASSWORD));
        
        // extra mail.smtp. properties from user
        Properties smtpProperties = new Properties();
        for (String key : data.keySet()) {
            if (key.startsWith("mail.smtp.")) {
                smtpProperties.put(key, data.getString(key));
            }
        }
        if (mailInfo.getSmtpProperties() == null) {
            mailInfo.setSmtpProperties(smtpProperties);
        } else {
            mailInfo.getSmtpProperties().putAll(smtpProperties);
        }

        
        return mailInfo;
    }
