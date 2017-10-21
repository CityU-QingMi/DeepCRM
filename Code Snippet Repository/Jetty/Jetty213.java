    @Test
    public void testScopeBehavior() throws Exception
    {
        ScopedInstance<WebSocketScopeContext> wsScopeBean = newInstance(WebSocketScopeContext.class);
        WebSocketScopeContext wsScope = wsScopeBean.instance;

        wsScope.create();
        Meal meal1;
        try
        {
            wsScope.begin();
            ScopedInstance<Meal> meal1Bean = newInstance(Meal.class);
            meal1 = meal1Bean.instance;
            ScopedInstance<Meal> meal2Bean = newInstance(Meal.class);
            Meal meal2 = meal2Bean.instance;
            
            assertThat("Meals are not the same",meal1,not(sameInstance(meal2)));
            
            assertThat("Meal 1 Entree Constructed",meal1.getEntree().isConstructed(),is(true));
            assertThat("Meal 1 Side Constructed",meal1.getSide().isConstructed(),is(true));
            
/**/
/**/
/**/
            assertThat("Meal parts not the same",meal1.getEntree(),sameInstance(meal1.getSide()));
            assertThat("Meal entrees are the same",meal1.getEntree(),sameInstance(meal2.getEntree()));
            assertThat("Meal sides are the same",meal1.getSide(),sameInstance(meal2.getSide()));

            meal1Bean.destroy();
            meal2Bean.destroy();
        }
        finally
        {
            wsScope.end();
        }
        
        Food entree1 = meal1.getEntree();
        Food side1 = meal1.getSide();

        assertThat("Meal 1 entree destroyed",entree1.isDestroyed(),is(false));
        assertThat("Meal 1 side destroyed",side1.isDestroyed(),is(false));
        wsScope.destroy();

        // assertThat("Meal 1 entree destroyed",entree1.isDestroyed(),is(true));
        // assertThat("Meal 1 side destroyed",side1.isDestroyed(),is(true));
        wsScopeBean.destroy();
    }
