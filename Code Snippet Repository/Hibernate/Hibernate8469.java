    @Test
    @TestForIssue(jiraKey = "")
    public void testNonExistentLazyInitOutsideTransaction() {
        Session s = openSession();
        s.beginTransaction();
        Child loadedChild = s.load(Child.class, -1L);
        s.getTransaction().commit();
        s.close();

        try {
            loadedChild.getParent();
            fail("lazy init did not fail on non-existent proxy");
        } catch (LazyInitializationException e) {
            assertNotNull(e.getMessage());
        }
    }
