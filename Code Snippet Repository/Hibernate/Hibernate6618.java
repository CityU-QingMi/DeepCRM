    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object entity) throws HibernateException {
        UUID uuid = UUID.randomUUID();
        String sud = uuid.toString();
        System.out.println("uuid="+uuid);
        sud = sud.replaceAll("-", "");
        
        BigInteger integer = new BigInteger(sud,16);

        System.out.println("bi ="+integer.toString() );
        return integer;
    }
