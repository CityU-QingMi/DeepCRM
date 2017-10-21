    @Test
    public void testGetBeans() throws Exception
    {
        TestContainerLifeCycle root = new TestContainerLifeCycle();
        TestContainerLifeCycle left = new TestContainerLifeCycle();
        root.addBean(left);
        TestContainerLifeCycle right = new TestContainerLifeCycle();
        root.addBean(right);
        TestContainerLifeCycle leaf = new TestContainerLifeCycle();
        right.addBean(leaf);

        root.addBean(Integer.valueOf(0));
        root.addBean(Integer.valueOf(1));
        left.addBean(Integer.valueOf(2));
        right.addBean(Integer.valueOf(3));
        leaf.addBean(Integer.valueOf(4));
        leaf.addBean("leaf");

        assertThat(root.getBeans(Container.class), containsInAnyOrder(left,right));
        assertThat(root.getBeans(Integer.class), containsInAnyOrder(Integer.valueOf(0),Integer.valueOf(1)));
        assertThat(root.getBeans(String.class), containsInAnyOrder());

        assertThat(root.getContainedBeans(Container.class), containsInAnyOrder(left,right,leaf));
        assertThat(root.getContainedBeans(Integer.class), containsInAnyOrder(Integer.valueOf(0),Integer.valueOf(1),Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4)));
        assertThat(root.getContainedBeans(String.class), containsInAnyOrder("leaf"));
    }
