    @Test
    public void testSendMailJobNoAuthentication() throws Exception {
        JobDetail job = newJob(SendMailJob.class)
                .withIdentity("job1", "group1").build();

        JobDataMap jobData = job.getJobDataMap();
        jobData.put(SendMailJob.PROP_SMTP_HOST, "localhost");
        jobData.put(SendMailJob.PROP_SENDER, "sender@host.com");
        jobData.put(SendMailJob.PROP_RECIPIENT, "receiver@host.com");
        jobData.put(SendMailJob.PROP_SUBJECT, "test subject");
        jobData.put(SendMailJob.PROP_MESSAGE, "do not reply");
        jobData.put("mail.smtp.port", "2500");

        SimpleTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        simpleSchedule().withIntervalInSeconds(120)
                                .withRepeatCount(1)).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        
        jobListener.barrier.await(30, TimeUnit.SECONDS);

        assertThat(wiser.getMessages().size(), equalTo(1));

        WiserMessage message = wiser.getMessages().get(0);
        System.out.println(message);
        System.out.println(message.getMimeMessage().getSubject());
        assertThat(message.getEnvelopeSender(), equalTo("sender@host.com"));
        assertThat(message.getEnvelopeReceiver(), equalTo("receiver@host.com"));
        assertThat(message.getMimeMessage().getSubject(), equalTo("test subject"));
        assertThat(IOUtils.toString(message.getMimeMessage().getInputStream())
                .trim(), equalTo("do not reply"));
    }
