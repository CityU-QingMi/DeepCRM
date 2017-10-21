    @Test
    public void testBasicBehavior() throws Exception
    {
        ScopedInstance<Meal> meal1Bean = newInstance(Meal.class);
        Meal meal1 = meal1Bean.instance;
        ScopedInstance<Meal> meal2Bean = newInstance(Meal.class);
        Meal meal2 = meal2Bean.instance;

        assertThat("Meals are not the same",meal1,not(sameInstance(meal2)));

        assertThat("Meal 1 Entree Constructed",meal1.getEntree().isConstructed(),is(true));
        assertThat("Meal 1 Side Constructed",meal1.getSide().isConstructed(),is(true));

        assertThat("Meal parts not the same",meal1.getEntree(),not(sameInstance(meal1.getSide())));
        assertThat("Meal entrees are the same",meal1.getEntree(),not(sameInstance(meal2.getEntree())));
        assertThat("Meal sides are the same",meal1.getSide(),not(sameInstance(meal2.getSide())));
    }
