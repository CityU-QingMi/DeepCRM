    protected MimeMessage prepareMimeMessage(MailInfo mailInfo)
        throws MessagingException {
        Session session = getMailSession(mailInfo);

        MimeMessage mimeMessage = new MimeMessage(session);

        Address[] toAddresses = InternetAddress.parse(mailInfo.getTo());
        mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);

        if (mailInfo.getCc() != null) {
            Address[] ccAddresses = InternetAddress.parse(mailInfo.getCc());
            mimeMessage.setRecipients(Message.RecipientType.CC, ccAddresses);
        }

        mimeMessage.setFrom(new InternetAddress(mailInfo.getFrom()));
        
        if (mailInfo.getReplyTo() != null) {
            mimeMessage.setReplyTo(new InternetAddress[]{new InternetAddress(mailInfo.getReplyTo())});
        }
        
        mimeMessage.setSubject(mailInfo.getSubject());
        
        mimeMessage.setSentDate(new Date());

        setMimeMessageContent(mimeMessage, mailInfo);

        return mimeMessage;
    }
