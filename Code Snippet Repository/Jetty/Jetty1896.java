    private Derived[] getArrayTypeAttribute()
    {
        derivedManaged = new DerivedManaged();
        mBeanDerivedManaged = new ObjectMBean(derivedManaged);
        MBeanContainer mBeanDerivedManagedContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        mBeanDerivedManaged.setMBeanContainer(mBeanDerivedManagedContainer);
        Derived derived0 = new Derived();
        mBeanDerivedManagedContainer.beanAdded(null,derived0);
        Derived[] derivedes = new Derived[3];
        for (int i = 0; i < 3; i++)
        {
            derivedes[i] = new Derived();
        }
        derivedManaged.setAddresses(derivedes);
        mBeanDerivedManaged.getMBeanInfo();
        ArrayList<Derived> aliasNames = new ArrayList<Derived>(Arrays.asList(derivedes));
        derivedManaged.setAliasNames(aliasNames);
        return derivedes;
    }
