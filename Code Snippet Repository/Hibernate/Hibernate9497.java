    @Test
    public void testInsertWithForeignKey() {
        Session session = sessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Message msg = new Message();
        final String messageId = "message_id";
        msg.setId(messageId);
        msg.setContent("message_content");
        msg.setSubject("message_subject");
        session.save(msg);

        tx.commit();
        session.close();

        StatelessSession statelessSession = sessionFactory().openStatelessSession();
        tx = statelessSession.beginTransaction();

        MessageRecipient signature = new MessageRecipient();
        signature.setId("recipient");
        signature.setEmail("recipient@hibernate.org");
        signature.setMessage(msg);
        statelessSession.insert(signature);

        tx.commit();

        cleanup();
    }
