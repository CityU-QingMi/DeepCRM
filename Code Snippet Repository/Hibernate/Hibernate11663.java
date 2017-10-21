      @Override
      protected Operation getOperation() {
         if (accessType == AccessType.READ_ONLY) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Operation operation;
            int r = random.nextInt(30);
            if (r == 0 && INVALIDATE_REGION) operation = new InvalidateCache();
            else if (r < 5) operation = new QueryFamilies();
            else if (r < 10) operation = new RemoveFamily(r < 12);
            else operation = new ReadFamily(r < 20);
            return operation;
         } else {
            return super.getOperation();
         }
      }
