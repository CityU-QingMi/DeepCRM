    public void testSubscriptionGroupCRUD() throws Exception {
        
        PlanetManager planet = WebloggerFactory.getWeblogger().getPlanetManager();
        
        // retrieve subscriptions and add to group
        Subscription sub1 = planet.getSubscriptionById(testSub1.getId());
        Subscription sub2 = planet.getSubscriptionById(testSub2.getId());
        PlanetGroup group = planet.getGroupById(testGroup1.getId());
        
        // make sure no subs in group yet
        assertEquals(0, group.getSubscriptions().size());
        
        // add
        group.getSubscriptions().add(sub1);
        sub1.getGroups().add(group);

        group.getSubscriptions().add(sub2);
        sub2.getGroups().add(group);
        
        planet.saveGroup(group);
        TestUtils.endSession(true);
        
        // verify
        group = null;
        group = planet.getGroupById(testGroup1.getId());
        sub1 = planet.getSubscriptionById(testSub1.getId());
        sub2 = planet.getSubscriptionById(testSub2.getId());
        assertEquals(2, group.getSubscriptions().size());
        
        // remove
        group.getSubscriptions().remove(sub1);
        sub1.getGroups().remove(group);
        
        group.getSubscriptions().remove(sub2);
        sub2.getGroups().remove(group);

        planet.saveGroup(group);
        TestUtils.endSession(true);
        
        // verify
        group = null;
        group = planet.getGroupById(testGroup1.getId());
        assertEquals(0, group.getSubscriptions().size());
    }
