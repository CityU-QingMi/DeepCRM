      protected void withSession(Consumer<Session> consumer) throws Exception {
         int node = threadNode.get();
         Session s = sessionFactory(node).openSession();
         Transaction tx = s.getTransaction();
         tx.begin();
         try {
            consumer.accept(s);
         } catch (Exception e) {
            tx.markRollbackOnly();
            throw e;
         } finally {
            try {
               if (!rolledBack && tx.getStatus() == TransactionStatus.ACTIVE) {
                  log.trace("Hibernate commit begin");
                  tx.commit();
                  log.trace("Hibernate commit end");
               } else {
                  log.trace("Hibernate rollback begin");
                  tx.rollback();
                  log.trace("Hibernate rollback end");
               }
            } catch (Exception e) {
               log.trace("Hibernate commit or rollback failed, status is " + tx.getStatus(), e);
               if (tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                  tx.rollback();
               }
               throw e;
            } finally {
               // cannot close before XA commit since force increment requires open connection
               s.close();
            }
         }
      }
