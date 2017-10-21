   protected Operation getOperation() {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      Operation operation;
      int r = random.nextInt(100);
      if (r == 0 && INVALIDATE_REGION) operation = new InvalidateCache();
      else if (r < 5) operation = new QueryFamilies();
      else if (r < 10) operation = new RemoveFamily(r < 6);
      else if (r < 20) operation = new UpdateFamily(r < 12, random.nextInt(1, 3));
      else if (r < 35) operation = new AddMember(r < 25);
      else if (r < 50) operation = new RemoveMember(r < 40);
      else operation = new ReadFamily(r < 75);
      return operation;
   }
