    @Ignore
    @Test
    public void testRealAccountSendMail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("xxx", "xxx");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("xxx@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("xxx@gmail.com"));
            message.setSubject("Test Subject");
            message.setText("Test message");
            Transport.send(message);
            System.out.println("Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
